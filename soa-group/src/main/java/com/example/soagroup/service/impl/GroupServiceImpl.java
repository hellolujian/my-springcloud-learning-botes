package com.example.soagroup.service.impl;

import com.example.soagroup.mapper.GroupMapper;
import com.example.soagroup.model.Group;
import com.example.soagroup.service.GroupService;
import com.netflix.discovery.converters.Auto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lujian01
 * @Date: 2019/5/17 20:27
 * @Description:
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Group getGroupById(Integer groupId) {
        return groupMapper.getGroupById(groupId);
    }
}
