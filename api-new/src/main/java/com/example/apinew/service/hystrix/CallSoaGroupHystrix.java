package com.example.apinew.service.hystrix;

import com.example.apinew.model.GroupModel;
import com.example.apinew.service.GroupService;
import org.springframework.stereotype.Component;

/**
 * @Author: lujian01
 * @Date: 2019/5/26 19:57
 * @Description:
 */
@Component
public class CallSoaGroupHystrix implements GroupService {

    @Override
    public GroupModel callSoaGroup(Integer groupId) {
        return new GroupModel();
    }
}
