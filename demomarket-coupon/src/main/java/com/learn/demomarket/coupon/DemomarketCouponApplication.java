package com.learn.demomarket.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.demomarket.coupon.dao")
public class DemomarketCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketCouponApplication.class, args);
    }

}
