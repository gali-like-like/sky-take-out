package com.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
public class SkyApplication {

    // Knife4j接口文档 http://localhost:8080/doc.html
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
    }
}
