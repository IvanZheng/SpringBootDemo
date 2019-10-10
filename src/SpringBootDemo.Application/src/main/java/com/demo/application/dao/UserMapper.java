package com.demo.application.dao;

import com.demo.domain.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUser(String id);
}
