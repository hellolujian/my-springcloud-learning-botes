package com.example.soacontent.mapper;

import com.example.soacontent.model.ContentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author: lujian01
 * @Date: 2019/5/18 14:49
 * @Description:
 */
@Component
public interface ContentMapper {

    ContentModel getContentById(@Param("contentId") Long contentId);
}
