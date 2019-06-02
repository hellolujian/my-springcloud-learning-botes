package com.example.apicommunity.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.apicommunity.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:10
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/v1.1")
public class ApiController {

    @Autowired
    private APIService apiService;

    @GetMapping(value = "/groups/{groupId}/contents/{contentId}")
    public JSONObject getGroupAndContentInfo(@PathVariable(value = "groupId") Integer groupId,
                                             @PathVariable("contentId") Long contentId) {
        return apiService.getGroupAndContentInfo(groupId, contentId);
    }
}
