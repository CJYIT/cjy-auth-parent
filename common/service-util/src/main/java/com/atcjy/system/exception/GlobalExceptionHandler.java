package com.atcjy.system.exception;

import com.atcjy.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/****
 * 有特定就执行特定异常，没有特定就执行全局异常处理类
 * @Author:cjy
 * @Description: com.atcjy.system.exception
 * @Date
 *****/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)   //全局异常处理器
    @ResponseBody  //返回数据给全都页面
    public Result error(Exception e){
        System.out.println("全局...");
        e.printStackTrace();  //打印异常信息
        return Result.fail().message("执行了全局异常处理");
    }

    //2特定异常处理
    @ExceptionHandler(ArithmeticException.class)   //全局异常处理器
    @ResponseBody  //返回数据给全都页面
    public Result error(ArithmeticException e){
        System.out.println("特定...");
        e.printStackTrace();  //打印异常信息
        return Result.fail().message("执行了特定异常处理");
    }

    //3自定义异常处理
    @ExceptionHandler(SelfException.class)   //全局异常处理器
    @ResponseBody  //返回数据给全都页面
    public Result error(SelfException e){
        System.out.println("特定...");
        e.printStackTrace();  //打印异常信息
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}
