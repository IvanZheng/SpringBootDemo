package com.demo.application.services;

import com.demo.application.dao.UserMapper;
import com.demo.domain.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUser(String id) {
        return userMapper.selectUser(id);
    }
}
