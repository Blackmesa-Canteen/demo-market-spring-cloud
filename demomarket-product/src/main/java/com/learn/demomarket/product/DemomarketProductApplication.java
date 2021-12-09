package com.learn.demomarket.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.learn.demomarket.product.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class DemomarketProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketProductApplication.class, args);
    }

}
