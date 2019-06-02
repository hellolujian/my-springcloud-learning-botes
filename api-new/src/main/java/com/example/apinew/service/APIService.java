package com.example.apinew.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:09
 * @Description:
 */
public interface APIService {

    JSONObject getGroupAndContentInfo(Integer groupId, Long contentId);
}
