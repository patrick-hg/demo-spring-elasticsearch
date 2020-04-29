package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping
    public List<Tweet> getAll() {
        List<Tweet> tweets = new ArrayList<>();
        tweetRepository.findAll().forEach(tweets::add);
        return tweets;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Tweet persist(@RequestBody Tweet tweet) {
        if (Strings.isEmpty(tweet.getId())) {
            tweet.setCreationDate(new Date());
        }
        tweet.setTags(getTags(tweet.getContent()));
        return tweetRepository.save(tweet);
    }

    private List getTags(String text) {
        String[] tags = text.split(" ");
        return Arrays.stream(tags)
                .filter(s -> s.startsWith("#"))
                .map(s -> s.substring(1))
                .collect(Collectors.toList());
    }

}
