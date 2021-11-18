package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void persistUser(User user) {
        if (findByUsername(user.getUsername()).isEmpty()) {     // seulement s'il n'existe pas
            userRepository.save(user);
        }
    }

    public void persistAll(List<User> users) {
        users.forEach(this::persistUser);
    }
}
