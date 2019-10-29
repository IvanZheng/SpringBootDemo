package com.demo.portal.controllers;

import com.demo.application.dao.UserMapper;
import com.demo.application.services.UserService;
import com.demo.domain.models.*;
import com.demo.infrastructure.Page;
import com.demo.portal.models.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public Page<UserDto> get(@PathVariable Integer pageIndex, @PathVariable int pageSize, @RequestParam(required = false) String name)
    {
        Page<UserDto> page = new Page<>(pageIndex, pageSize);
        page.getParams().put("name", name);
        UserDto[] users = userService.selectUsers(page)
                .stream()
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toArray(UserDto[]::new);
        page.setResults(users);
        return page;
    }
}
