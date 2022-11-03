package com.atcjy.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/****
 * 启动类有个扫描规则，扫描当前包及其子包，写测试类的时候为了方便将包结构建成与启动类的包相似
 * @Author:cjy
 * @Description: com.atcjy.system
 * @Date
 *****/
@SpringBootApplication
@MapperScan("com.atcjy.system.mapper")  //扫描动态生成的实现类
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

}
