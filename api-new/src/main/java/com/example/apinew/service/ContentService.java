package com.example.apinew.service;

import com.example.apinew.model.ContentModel;
import com.example.apinew.service.hystrix.CallSoaGroupHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:36
 * @Description:
 */
@FeignClient(value = "soa-content", fallback = CallSoaGroupHystrix.class)
public interface ContentService {

    ContentModel callSoaContent(Integer contentId);
}
