package com.example.apicommunity.restclient;

import com.example.apicommunity.model.ContentModel;
import com.example.apicommunity.model.GroupModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.acl.Group;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:35
 * @Description:
 */
@Service
public class SoaGroupRest {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "handleCallError", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "10")
    })

    public GroupModel callSoaGroup(Integer groupId) {
        return restTemplate.getForObject("http://soa-group/api/group/v1.1/groups/" + groupId, GroupModel.class);
    }

    public GroupModel handleCallError(Integer groupId) {
        return new GroupModel();
    }
}
