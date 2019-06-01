package com.example.soacontent.service.impl;

import com.example.soacontent.mapper.ContentMapper;
import com.example.soacontent.model.ContentModel;
import com.example.soacontent.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lujian01
 * @Date: 2019/5/18 14:50
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public ContentModel getContentById(Long contentId) {
        return contentMapper.getContentById(contentId);
    }
}
