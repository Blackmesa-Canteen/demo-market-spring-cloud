package com.learn.demomarket.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.learn.demomarket.ware.dao")
@EnableDiscoveryClient
public class DemomarketWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketWareApplication.class, args);
    }

}
