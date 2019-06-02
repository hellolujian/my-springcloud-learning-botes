package com.example.apinew.restclient.hystrix;

import com.example.apinew.model.GroupModel;
import com.example.apinew.restclient.SoaGroupRest;
import org.springframework.stereotype.Component;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:57
 * @Description:
 */
@Component
public class SoaGroupRestHystrix implements SoaGroupRest {

    @Override
    public GroupModel callSoaGroup(Integer groupId) {
        return new GroupModel();
    }
}
