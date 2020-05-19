package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.model.User;
import com.keywer.article.demospringelasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User persist(@RequestBody User user) {
        return userService.persistUser(user);
    }

}
