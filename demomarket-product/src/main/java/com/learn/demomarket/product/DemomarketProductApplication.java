package com.learn.demomarket.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableRedisHttpSession     //开启springsession
@EnableCaching      //开启缓存功能
@MapperScan("com.learn.demomarket.product.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class DemomarketProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomarketProductApplication.class, args);
    }

}
