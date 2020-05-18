package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import com.keywer.article.demospringelasticsearch.service.TweetService;
import com.keywer.article.demospringelasticsearch.utils.Utils;
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

    @Autowired
    private TweetService tweetService;

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
        tweet.setTags(Utils.tagsFromText(tweet.getContent()));
        return tweetRepository.save(tweet);
    }

    @GetMapping(value = "/find-by-username")
    public List<Tweet> findByUsernameWithPagination(@RequestParam String username,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize,
                                                    @RequestParam Boolean useCustomQuery) {
        if (useCustomQuery) {
            return tweetService.findByUsernameUsingCustomQuery(username, pageNum, pageSize);
        }
        return tweetService.findByUsername(username, pageNum, pageSize);
    }

    @GetMapping(value = "/search")
    public List<Tweet> findByUsernameWithPagination(@RequestParam String text,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize) {
        return null;
    }
}
