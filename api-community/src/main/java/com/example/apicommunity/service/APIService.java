package com.example.apicommunity.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:09
 * @Description:
 */
public interface APIService {

    JSONObject getGroupAndContentInfo(Integer groupId, Long contentId);
}
