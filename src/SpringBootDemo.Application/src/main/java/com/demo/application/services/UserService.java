package com.demo.application.services;

import com.demo.application.dao.UserMapper;
import com.demo.domain.models.User;
import com.demo.infrastructure.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUser(String id) {
        return userMapper.selectUser(id);
    }

    public List<User> selectUsers(Page page)
    {
        return userMapper.selectUsers(page);
    }

    public User createUser(User addUser) {
        User user = new User(addUser.getName(), addUser.getGender(), addUser.getUserProfile(), null);
        userMapper.addUser(user);
        return user;
    }
}
