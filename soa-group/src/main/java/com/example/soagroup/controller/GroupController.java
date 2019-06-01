package com.example.soagroup.controller;

import com.example.soagroup.model.Group;
import com.example.soagroup.service.GroupService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lujian01
 * @Date: 2019/5/15 21:30
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/group/v1.1")
public class GroupController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/hello")
    public String getGroupInfo() {
        return "Hello, I am soa-group, my port is " + port;
    }


    @Value("${hello}")
    private String hello;

    @GetMapping(value = "/hello2")
    public String getHello() {
        return hello;
    }

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups/{groupId}")
    public Group getGroupById(@PathVariable Integer groupId) {
        return groupService.getGroupById(groupId);
    }
}
