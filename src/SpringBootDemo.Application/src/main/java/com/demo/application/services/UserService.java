package com.demo.application.services;

import com.demo.application.dao.UserMapper;
import com.demo.domain.models.User;
import com.demo.domain.repositories.UserRepository;
import com.demo.infrastructure.Page;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<User> findByName(String name){
        return userRepository.findByName(name);
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
        userMapper.insertUser(user);
        //userRepository.save(user);
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
