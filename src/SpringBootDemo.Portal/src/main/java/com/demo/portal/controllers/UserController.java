package com.demo.portal.controllers;

import com.demo.application.dao.UserMapper;
import com.demo.application.services.UserService;
import com.demo.domain.models.*;
import com.demo.infrastructure.Page;
import com.demo.portal.models.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping(value ="/{pageIndex}/{pageSize}")
    public Page<User> get(@PathVariable Integer pageIndex, @PathVariable int pageSize)
    {
        Page<User> page = new Page<>(pageIndex, pageSize);
        List<User> users = userService.selectUsers(page);
        page.setResults(users);
//        List<UserDto> userDtoList = new ArrayList<>();
//        for (User user: users) {
//            userDtoList.add(new UserDto(user.getId(), user.getName()));
//        }
        return page;
    }
}
