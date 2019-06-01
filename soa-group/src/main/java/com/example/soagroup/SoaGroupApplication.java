package com.example.soagroup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.soagroup.mapper")
public class SoaGroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaGroupApplication.class, args);
    }

}
