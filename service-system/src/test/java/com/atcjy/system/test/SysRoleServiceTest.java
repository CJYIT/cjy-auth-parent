package com.atcjy.system.test;

import com.atcjy.model.system.SysRole;
import com.atcjy.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/****
 * @Author:cjy
 * @Description: com.atcjy.system.test
 * @Date
 *****/
@SpringBootTest
public class SysRoleServiceTest {

    //注入service
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有
    @Test
    public void findAll(){
        //service方法实现
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

    //添加
    //2添加操作
    @Test
    public void testAdd() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色名4");
        sysRole.setRoleCode("testManager4");
        sysRole.setDescription("测试角色4");
        sysRoleService.save(sysRole);
        System.out.println(sysRole);
    }

    //3修改
    @Test
    public void update() {
        //根据id查询
        SysRole sysRole = sysRoleService.getById(5);

        //设置修改的值
        sysRole.setDescription("管理员");

        //调用方法实现修改
        sysRoleService.updateById(sysRole);
    }

    //4id删除
    @Test
    public void removeId() {
        boolean rows = sysRoleService.removeById(3);
    }

    //5批量删除
    @Test
    public void testBatchDelete() {
        sysRoleService.removeByIds(Arrays.asList(4,5));
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
        List<SysRole> list = sysRoleService.list(wrapper);
        System.out.println(list);
    }
}
