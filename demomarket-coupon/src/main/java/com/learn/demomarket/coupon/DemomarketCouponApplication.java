package com.learn.demomarket.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.learn.demomarket.coupon.dao")
@EnableDiscoveryClient
public class DemomarketCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketCouponApplication.class, args);
    }

}
