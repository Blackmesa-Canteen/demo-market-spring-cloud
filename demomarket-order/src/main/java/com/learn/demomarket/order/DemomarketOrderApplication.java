package com.learn.demomarket.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.learn.demomarket.order.dao")
@EnableDiscoveryClient
public class DemomarketOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketOrderApplication.class, args);
    }

}
