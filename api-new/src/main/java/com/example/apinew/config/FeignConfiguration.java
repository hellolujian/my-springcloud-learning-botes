package com.example.apinew.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Author: lujian01
 * @Date: 2019/6/10 18:22
 * @Description:
 */
//@Configuration
//@ConditionalOnClass({HystrixCommand.class, HystrixFeign.class})
//public class FeignConfiguration {
//
//    public static int connectTimeOutMillis = 5000; // 超时时间
//    public static int readTimeOutMillis = 5000;
//
//    @Bean
//    public Request.Options options() {
//        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
//    }
//
//    @Bean
//    public Retryer feignRetryer() {
//        Retryer retryer = new Retryer.Default(100, 1000, 4);
//        return retryer;
//    }
//
//    @Bean
//    public Feign.Builder feignHystrixBuilder() {
//        return HystrixFeign.builder().setterFactory(new SetterFactory() {
//            @Override
//            public HystrixCommand.Setter create(Target<?> target, Method method) {
//                return HystrixCommand.Setter
//                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
//                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));
//            }
//        });
//    }
//}
