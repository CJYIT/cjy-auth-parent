package com.atcjy.system.mapper;

import com.atcjy.model.system.SysRole;
import com.atcjy.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;     //BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/****
 * @Author:cjy
 * @Description: com.atcjy.system.mapper
 * @Date
 *****/
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    //条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam,@Param("vo") SysRoleQueryVo sysRoleQueryVo);//vo对应sysRoleQueryVo
}
