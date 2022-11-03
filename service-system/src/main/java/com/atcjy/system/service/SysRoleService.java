package com.atcjy.system.service;

import com.atcjy.model.system.SysRole;
import com.atcjy.model.vo.AssginRoleVo;
import com.atcjy.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/****
 * @Author:cjy
 * @Description: com.atcjy.system.service
 * @Date
 *****/
public interface SysRoleService extends IService<SysRole> {
    //条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRolesByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
