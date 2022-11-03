package com.atcjy.system.service;

import com.atcjy.model.system.SysUser;
import com.atcjy.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atcjy
 * @since 2022-11-03
 */
public interface SysUserService extends IService<SysUser> {

    //用户列表
    //@Transactional
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateStatus(String id, Integer status);
}
