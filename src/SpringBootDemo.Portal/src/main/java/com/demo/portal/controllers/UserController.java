package com.demo.portal.controllers;

import com.demo.portal.domain.models.*;
import com.demo.portal.models.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto get(@PathVariable String id) {
        //UserDto user = userMapper.selectUser(id);
        User user = new User();
        return new UserDto(id, "ivan");
    }
}
