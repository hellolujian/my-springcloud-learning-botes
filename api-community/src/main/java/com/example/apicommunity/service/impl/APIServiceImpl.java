package com.example.apicommunity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.apicommunity.model.ContentModel;
import com.example.apicommunity.model.GroupModel;
import com.example.apicommunity.restclient.SoaContentRest;
import com.example.apicommunity.restclient.SoaGroupRest;
import com.example.apicommunity.service.APIService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:10
 * @Description:
 */
@Service
public class APIServiceImpl implements APIService {

    @Autowired
    private SoaGroupRest groupRest;

    @Autowired
    private SoaContentRest contentRest;

    @Override
    public JSONObject getGroupAndContentInfo(Integer groupId, Long contentId) {

        GroupModel groupModel = groupRest.callSoaGroup(groupId);
        ContentModel contentModel = contentRest.callSoaContent(contentId);
        JSONObject result = new JSONObject();
        result.putAll(JSON.parseObject(JSON.toJSONString(groupModel)));
        result.putAll(JSON.parseObject(JSON.toJSONString(contentModel)));
        return result;
    }
}
