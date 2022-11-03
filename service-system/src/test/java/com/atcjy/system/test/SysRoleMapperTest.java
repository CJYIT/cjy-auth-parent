package com.atcjy.system.test;

import com.atcjy.model.system.SysRole;
import com.atcjy.system.mapper.SysRoleMapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/****
 * 启动类有个扫描规则，扫描当前包及其子包，写测试类的时候为了方便将包结构建成与启动类的包相似,
 * 这里创建system的子包
 * @Author:cjy
 * @Description: com.atcjy
 * @Date
 *****/
@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    //7条件删除
    @Test
    public void testDelete() {
        //创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        wrapper.eq("role_name","用户管理员");  //两个字段，表字段名、操作的条件
        //调用删除方法
        sysRoleMapper.delete(wrapper);

    }

    //6条件查询
    @Test
    public void testSelect() {
        //创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        //wrapper.eq("role_name","用户管理员");  //两个字段，表字段名、要查的条件
        wrapper.like("role_name","管理员");  //like模糊查询
        //调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println(list);
    }

    //5批量删除
    @Test
    public void testBatchDelete() {
        sysRoleMapper.deleteBatchIds(Arrays.asList(4,5));
    }

    //4id删除
    @Test
    public void deleteId() {
        int rows = sysRoleMapper.deleteById(5);
    }


    //3修改
    @Test
    public void update() {
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(5);

        //设置修改的值
        sysRole.setDescription("系统管理员");

        //调用方法实现修改
        sysRoleMapper.updateById(sysRole);
    }

    //2添加操作
    @Test
    public void testAdd() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色名3");
        sysRole.setRoleCode("testManager3");
        sysRole.setDescription("测试角色3");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }

    //1查询所有用户记录
    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<SysRole> users = sysRoleMapper.selectList(null);
        for (SysRole sysRole : users) {
            System.out.println("sysRole = " + sysRole);
        }
    }

}
