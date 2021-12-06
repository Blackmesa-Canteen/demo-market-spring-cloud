package com.learn.demomarketthirdparty;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class DemomarketThirdPartyApplicationTests {

    @Autowired
    OSS ossClient;

    @Test
    void contextLoads() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("/Users/shaotienlee/Code/学习项目们/SpringCloud/demo-Mail-demo/demomarket-third-party/src/main/java/com/learn/demomarketthirdparty/DemomarketThirdPartyApplication.java");

        ossClient.putObject("demo-114514", "demo.java", inputStream);

        ossClient.shutdown();

        System.out.println("ok");
    }

}
