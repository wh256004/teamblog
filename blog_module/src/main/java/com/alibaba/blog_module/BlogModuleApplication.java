package com.alibaba.blog_module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;

// 或者使用 spring.profiles.active 工具类
@SpringBootApplication()
@PropertySource(value={"classpath:application.properties","application-db.properties","application-common.properties","application-server.properties"})
@MapperScan({"com.alibaba.blog_db.*"})
@ComponentScan(basePackages = {"com.alibaba.blog_module.controller.*","como.alibaba.blog_server.*"})
public class BlogModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogModuleApplication.class, args);
    }
    @RequestMapping("/test")
    public String hello(){
        return "heello";
    }

}
