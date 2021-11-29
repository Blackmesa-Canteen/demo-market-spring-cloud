package com.learn.demomarket.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.demomarket.product.dao")
public class DemomarketProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketProductApplication.class, args);
    }

}
