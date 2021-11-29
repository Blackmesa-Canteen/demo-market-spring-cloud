package com.learn.demomarket.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.demomarket.order.dao")
public class DemomarketOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketOrderApplication.class, args);
    }

}
