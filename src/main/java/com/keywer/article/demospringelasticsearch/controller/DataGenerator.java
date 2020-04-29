package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import com.keywer.article.demospringelasticsearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/generate")
public class DataGenerator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    private static Random rand = new Random();


    @GetMapping("/users/{name}/{nb}")
    public void generateUsers(@PathVariable("name") String name, @PathVariable("nb") Long number) {
        List<User> users = new ArrayList<>();

        for (int i=0; i < number; i++) {
            User user = new User(name + i, name.toUpperCase() + " " + i);
            user.setCreationDate(ONE_YEAR_AGO);
            users.add(user);
        }

        userRepository.saveAll(users);
    }

    @GetMapping("/tweets/{nb}")
    public void generateTweets(@PathVariable("nb") Long number) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        int nbUsers = users.size();

        List<Tweet> tweets = new ArrayList<>();
        for (int i=0; i < number; i++) {

            Tweet tweet = new Tweet("",
                    localizationSamples.get(randomNum(6)),
                    users.get(randomNum(nbUsers)).getUsername());

            tweet.setCreationDate(randomDate());
            tweets.add(tweet);
        }
        tweetRepository.saveAll(tweets);
    }

    private int randomNum(int max) {
        return rand.nextInt(max);
    }

    private Date randomDate() {
        return new Date(NOW - aDay * randomNum(365));
    }

    private final static List<String> localizationSamples = Arrays.asList("Paris, France", "Lyon, France", "Marseille, France", "Rome, Italie", "Berlin, Allemagne", "Londres, Angletaire");

    private final long aDay = TimeUnit.DAYS.toMillis(1);
    private final long NOW = new Date().getTime();
    private final Date TODAY = new Date();
    private final Date ONE_YEAR_AGO = new Date(NOW - aDay * 365);
}
