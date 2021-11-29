package com.learn.demomarket.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.demomarket.member.dao")
public class DemomarketMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketMemberApplication.class, args);
    }

}
