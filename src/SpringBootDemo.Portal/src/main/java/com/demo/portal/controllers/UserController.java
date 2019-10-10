package com.demo.portal.controllers;

import com.demo.application.dao.UserMapper;
import com.demo.application.services.UserService;
import com.demo.domain.models.*;
import com.demo.portal.models.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto get(@PathVariable String id) {
        User user = userService.selectUser(id);
        return new UserDto(id, user.getName());
    }
}
