package com.example.apinew.restclient;

import com.example.apinew.model.GroupModel;
import com.example.apinew.restclient.hystrix.SoaGroupRestHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:52
 * @Description:
 */
@FeignClient(value = "soa-group", fallback = SoaGroupRestHystrix.class)
public interface SoaGroupRest {

    @GetMapping(value = "/api/group/v1.1/groups/{groupId}")
    GroupModel callSoaGroup(@PathVariable(value = "groupId") Integer groupId);
}
