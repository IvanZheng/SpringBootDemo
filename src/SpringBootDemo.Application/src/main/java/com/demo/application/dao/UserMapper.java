package com.demo.application.dao;

import com.demo.domain.models.User;
import com.demo.infrastructure.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User selectUser(String id);
    List<User> selectUsers(Page page);
}
