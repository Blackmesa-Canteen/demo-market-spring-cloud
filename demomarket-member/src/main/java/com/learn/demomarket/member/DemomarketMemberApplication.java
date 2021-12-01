package com.learn.demomarket.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.learn.demomarket.member.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.learn.demomarket.member.feign")
public class DemomarketMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketMemberApplication.class, args);
    }

}
