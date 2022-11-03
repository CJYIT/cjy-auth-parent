package com.atcjy.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/****
 * 自定义全局异常类
 * @Author:cjy
 * @Description: com.atcjy.system.exception
 * @Date
 *****/
@Data
@AllArgsConstructor //有参构造
@NoArgsConstructor  //无参构造
public class SelfException extends RuntimeException { //运行时异常
    private Integer code;  //状态码
    private String message; //信息
}
