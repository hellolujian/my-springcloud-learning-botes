package com.example.soacontent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.example.soacontent.mapper")
public class SoaContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaContentApplication.class, args);
    }

}
