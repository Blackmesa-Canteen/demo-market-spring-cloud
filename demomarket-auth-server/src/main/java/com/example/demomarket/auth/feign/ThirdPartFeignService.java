package com.example.demomarket.auth.feign;

import com.learn.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 996Worker
 * @program demo-Mail-demo
 * @description
 * @create 2021-12-19 22:14
 */
@FeignClient("demomarket-third-party")
public interface ThirdPartFeignService {

    @GetMapping(value = "/sms/sendCode")
    R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);

}