package com.example.apicommunity.restclient;

import com.example.apicommunity.model.ContentModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:17
 * @Description:
 */
@Service
public class SoaContentRest {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "handleCallError", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "20")
    })

    public ContentModel callSoaContent(Long contentId) {
        return restTemplate.getForObject("http://soa-content/api/content/v1.1/contents/" + contentId, ContentModel.class);
    }

    public ContentModel handleCallError(Long contentId) {
        return new ContentModel();
    }
}
