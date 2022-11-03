package com.atcjy.system.service.impl;

import com.atcjy.model.system.SysRole;
import com.atcjy.model.system.SysUserRole;
import com.atcjy.model.vo.AssginRoleVo;
import com.atcjy.model.vo.SysRoleQueryVo;
import com.atcjy.system.mapper.SysRoleMapper;
import com.atcjy.system.mapper.SysUserRoleMapper;
import com.atcjy.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:cjy
 * @Description: com.atcjy.system.service.impl
 * @Date
 *****/
@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo); //到SysRoleMapper中创建方法
        return pageModel;
    }

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //获取所有角色数据
        List<SysRole> roles = baseMapper.selectList(null);
        //根据用户id进行查询已分配的角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        //从userRoles集合中获取所有角色id
        ArrayList<String> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : userRolesList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);  //将所有的id放进集合userRoleIds
        }
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);//所有角色
        returnMap.put("userRoleIds",userRoleIds);//用户分配角色id集合
        return returnMap;
    }

    //给用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前的角色
        /*构造条件
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);*/
        //这里是逻辑删除  BaseEntry中注解@TableLogic表示逻辑删除
        // UPDATE sys_user_role SET is_deleted=1 WHERE id=? AND is_deleted=0
        //或者直接通过assginRoleVo获取id
        sysUserRoleMapper.deleteById(assginRoleVo.getUserId());
        //获得所有角色id，添加角色用户关系
        //角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }
}
