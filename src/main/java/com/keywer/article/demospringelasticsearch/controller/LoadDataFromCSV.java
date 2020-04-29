package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.model.User;
import com.keywer.article.demospringelasticsearch.service.UserService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDataFromCSV {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void loadData() {

        loadUsers();

    }

    private void loadUsers() {

        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/users.csv"), ';');
            String[] line;
            reader.skip(1);
            List<User> users = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                users.add(new User(line[0], line[1]));
            }
            userService.persistAll(users);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
