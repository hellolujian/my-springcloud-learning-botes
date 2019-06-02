package com.example.apinew.restclient.hystrix;

import com.example.apinew.model.ContentModel;
import com.example.apinew.model.GroupModel;
import com.example.apinew.restclient.SoaContentRest;
import org.springframework.stereotype.Component;

/**
 * @Author: lujian01
 * @Date: 2019/5/31 16:59
 * @Description:
 */
@Component
public class SoaContentRestHystrix implements SoaContentRest {

    @Override
    public ContentModel callSoaContent(Long contentId) {
        return new ContentModel();
    }
}
