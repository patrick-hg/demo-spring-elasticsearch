package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User persistUser(User user) {
        return userRepository.save(user);
    }
}
