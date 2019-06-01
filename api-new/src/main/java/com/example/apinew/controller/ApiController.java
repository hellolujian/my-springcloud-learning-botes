package com.example.apinew.controller;

import com.example.apinew.model.ContentModel;
import com.example.apinew.model.GroupModel;
import com.example.apinew.service.impl.ContentServiceImpl;
import com.example.apinew.service.GroupService;
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
    private ContentServiceImpl contentService;

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "/contents/{contentId}")
    public ContentModel getContentInfo(@PathVariable("contentId") Integer contentId) {
        return contentService.callSoaContent(contentId);
    }

    @GetMapping(value = "/groups/{groupId}")
    public GroupModel getGroupInfo(@PathVariable(value = "groupId") Integer groupId) {
        return groupService.callSoaGroup(groupId);
    }
}
