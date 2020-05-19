package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import com.keywer.article.demospringelasticsearch.model.User;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ImportFromCSV {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/import-data")
    public void importData() {
        importUsers();
        importTweets();
    }

    private void importUsers() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/users.csv"), ';');
            String[] line;
            reader.skip(1);
            List<User> users = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                users.add(new User(line[0].trim(), line[1].trim(), Utils.oneYearAgo()));
            }
            userRepository.saveAll(users);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void importTweets() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/tweets.csv"), ';');
            String[] line;
            reader.skip(1);
            List<Tweet> tweets = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                tweets.add(new Tweet(line[0].trim(), line[1].trim(), line[2].trim(), Utils.randomDateInRange(365)));
            }
            tweetRepository.saveAll(tweets);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
