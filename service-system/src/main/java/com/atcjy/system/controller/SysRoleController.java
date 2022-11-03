package com.atcjy.system.controller;

import com.atcjy.common.result.Result;
import com.atcjy.model.system.SysRole;
import com.atcjy.model.vo.AssginRoleVo;
import com.atcjy.model.vo.SysRoleQueryVo;
import com.atcjy.system.exception.SelfException;
import com.atcjy.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:cjy
 * @Description: com.atcjy.system.controller
 * @Date
 *****/
@Api(tags = "角色管理")  //Swagger2的注解,使得生成的接口文档有中文提示
@RestController   //包含@Controller注册容器和返回接口@ResponseBody
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //http://localhost:8800/admin/system/sysRole/findAll

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

    //7批量删除
    //得到多个id值，json中的数组形式[1,2,3]对应Java中的list集合
    //前面添加角色时候使用的是
    @ApiOperation("批量删除")
    //@PostMapping("batchRemove")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail("数据不存在");
        }
    }

    //6修改
    //修改之最终修改
    @ApiOperation("根据id修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //5修改之查询
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        //根据id查询
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //4添加
    //@RequestBody不能使用get提交方式
    //传递json格式数据，把json格式数据封装到对象里面 {...}
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //3条件分页查询
    //page当前页，limit每页显示的记录数
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysRoleQueryVo sysRoleQueryVo){ //使用VO类对象sysRoleQueryVo封装条件，在这里的条件就是一个RoleName，也可以直接使用变量
        //创建page对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        //调用service方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam,sysRoleQueryVo);//IPage是baomidou里面的一个对象metadata.IPage;
        //返回数据
        return Result.ok(pageModel);
    }

    //2逻辑删除接口
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}") //路径传值id
    public Result removeRole(@PathVariable Long id){
        //调用方法进行删除
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //1查询所有记录
    @ApiOperation("查询所有记录接口")
    @GetMapping("/findAll")
    public Result findAllRole() {
        /*try {
            int a = 10/0;
        }catch(Exception e) {
            throw new SelfException(20001,"出现自定义异常");
        }*/

        //TODO 模拟异常效果 ArithmeticException
        //int i = 9/0;
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);  //ok是静态的，可以直接传入数据
    }
}
