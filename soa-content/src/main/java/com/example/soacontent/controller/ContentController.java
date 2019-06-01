package com.example.soacontent.controller;

import com.example.soacontent.model.ContentModel;
import com.example.soacontent.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lujian01
 * @Date: 2019/5/18 14:47
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/content/v1.1")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping(value = "/contents/{contentId}")
    public ContentModel getContentById(@PathVariable(value = "contentId") Long contentId) {
        return contentService.getContentById(contentId);
    }
}
