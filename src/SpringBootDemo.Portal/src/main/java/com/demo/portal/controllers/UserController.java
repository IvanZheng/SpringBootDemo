package com.demo.portal.controllers;

import com.demo.application.services.UserService;
import com.demo.domain.models.User;
import com.demo.infrastructure.Page;
import com.demo.portal.models.UserDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CacheConfig(cacheNames = "default")
@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User post(@RequestBody User user) {
        user = userService.createUser(user);
        return user;
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    public User put(@RequestBody User user) {
        user = userService.updateUser(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable String id) {
        logger.debug("get user {}", id);
        logger.error("get user {}", id);
        User user = userService.selectUser(id);
        return user;
        //return new UserDto(id, user.getName());
    }

    //@Cacheable
    @RequestMapping(value = "/{pageIndex}/{pageSize}")
    public Page<User> get(@PathVariable int pageIndex,
                             @PathVariable int pageSize,
                             @RequestParam(required = false) String name) {
        logger.debug("get users {} {} {}", pageIndex, pageSize, name);
        Page<User> page = new Page<>(pageIndex, pageSize);
        page.setResults(userService.selectUsers(page));
        return page;

//        Page<UserDto> page = new Page<>(pageIndex, pageSize);
//        page.getParams().put("name", name);
//        UserDto[] users = userService.selectUsers(page)
//                .stream()
//                .map(u -> new UserDto(u.getId(), u.getName()))
//                .toArray(UserDto[]::new);
//        page.setResults(users);
//        return page;
    }
}
