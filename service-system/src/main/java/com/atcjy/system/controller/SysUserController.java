package com.atcjy.system.controller;


import com.atcjy.common.result.Result;
import com.atcjy.model.system.SysUser;
import com.atcjy.model.vo.SysUserQueryVo;
import com.atcjy.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atcjy
 * @since 2022-11-03
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("更改用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status){
        sysUserService.updateStatus(id,status);
        return Result.ok();

    }


    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo){
        //创建page对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam,sysUserQueryVo);//两参数分页参数和查询条件
        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user){
        boolean is_Success = sysUserService.save(user);
        if (is_Success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //修改用户之查询
    @ApiOperation("根据id查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }
    //修改用户之添加
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser user){
        boolean is_Success = sysUserService.updateById(user);//通过获取用户对象的id进行修改
        if (is_Success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean is_Success = sysUserService.removeById(id);//删除成功会返回1
        if (is_Success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}

