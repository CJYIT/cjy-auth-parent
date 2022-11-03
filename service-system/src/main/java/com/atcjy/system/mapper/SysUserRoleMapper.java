package com.atcjy.system.mapper;

import com.atcjy.model.system.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/****这里就不写service，直接使用controller操作mapper了
 * @Author:cjy
 * @Description: com.atcjy.system.mapper
 * @Date
 *****/
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
