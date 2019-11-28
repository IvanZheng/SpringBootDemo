package com.demo.application.services;

import com.demo.application.dao.UserMapper;
import com.demo.domain.models.User;
import com.demo.infrastructure.Page;
import org.springframework.dao.OptimisticLockingFailureException;
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
        User user = new User(addUser.getName(), addUser.getGender(), addUser.getUserProfile(), addUser.getCards());
        userMapper.addUser(user);
        return user;
    }

    public User updateUser(User updateUser) {
        User user = userMapper.selectUser(updateUser.getId());
        user.update(updateUser.getName(),
                updateUser.getGender(),
                updateUser.getUserProfile());
        if (!userMapper.updateUser(user))
        {
            throw new OptimisticLockingFailureException(String.format("user update failed!"));
        }
        return user;
    }
}
