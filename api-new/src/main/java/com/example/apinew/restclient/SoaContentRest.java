package com.example.apinew.restclient;

import com.example.apinew.model.ContentModel;
import com.example.apinew.restclient.hystrix.SoaContentRestHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:55
 * @Description:
 */
@FeignClient(value = "soa-content", fallback = SoaContentRestHystrix.class)
public interface SoaContentRest {

    @GetMapping(value = "/api/content/v1.1/contents/{contentId}")
    ContentModel callSoaContent(@PathVariable(value = "contentId") Long contentId);
}
