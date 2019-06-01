package com.example.soagroup.controller;

import com.example.soagroup.model.Group;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lujian01
 * @Date: 2019/5/18 14:37
 * @Description:
 */
@RestController
@RequestMapping("/api/group/v1.1/test")
public class TestController {

    @Value("${configInfo}")
    String selfConfigInfo;

    @GetMapping(value = "/self/config")
    public String getSelfConfigInfo() {
        return selfConfigInfo;
    }

}
