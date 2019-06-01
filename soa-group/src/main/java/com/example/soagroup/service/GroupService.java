package com.example.soagroup.service;

import com.example.soagroup.model.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @Author: lujian01
 * @Date: 2019/5/17 20:26
 * @Description:
 */
public interface GroupService {

    Group getGroupById(Integer groupId);
}
