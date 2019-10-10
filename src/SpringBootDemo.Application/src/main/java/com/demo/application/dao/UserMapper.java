package com.demo.application.dao;

import com.demo.domain.models.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUser(String id);
}
