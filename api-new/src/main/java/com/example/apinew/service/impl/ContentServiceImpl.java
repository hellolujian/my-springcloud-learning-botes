package com.example.apinew.service.impl;

import com.example.apinew.model.ContentModel;
import com.example.apinew.service.ContentService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:17
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "handleCallError")
    public ContentModel callSoaContent(Integer contentId) {
        return restTemplate.getForObject("http://soa-content/api/content/v1.1/contents/" + contentId, ContentModel.class);
    }

    public ContentModel handleCallError(Integer contentId) {
        return new ContentModel();
    }
}
