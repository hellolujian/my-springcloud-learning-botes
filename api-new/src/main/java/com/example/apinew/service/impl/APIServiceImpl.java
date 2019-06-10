package com.example.apinew.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.apinew.model.ContentModel;
import com.example.apinew.model.GroupModel;
import com.example.apinew.restclient.SoaContentRest;
import com.example.apinew.restclient.SoaGroupRest;
import com.example.apinew.service.APIService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: lujian01
 * @Date: 2019/6/1 15:10
 * @Description:
 */
@Service
public class APIServiceImpl implements APIService {

    @Resource
    private SoaGroupRest groupRest;

    @Resource
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
