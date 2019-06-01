package com.example.soagroup.mapper;

import com.example.soagroup.model.Group;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lujian01
 * @Date: 2019/5/17 20:37
 * @Description:
 */
public interface GroupMapper {

    Group getGroupById(@Param("groupId") Integer groupId);
}
