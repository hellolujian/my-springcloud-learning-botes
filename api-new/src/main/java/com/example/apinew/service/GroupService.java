package com.example.apinew.service;

import com.example.apinew.model.GroupModel;
import com.example.apinew.service.hystrix.CallSoaGroupHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:36
 * @Description:
 */
@FeignClient(value = "soa-group", fallback = CallSoaGroupHystrix.class)
public interface GroupService {

    @GetMapping(value = "/api/group/v1.1/groups/{groupId}")
    GroupModel callSoaGroup(@PathVariable(value = "groupId") Integer groupId);
}
