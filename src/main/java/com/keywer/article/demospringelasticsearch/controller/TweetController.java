package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import com.keywer.article.demospringelasticsearch.service.TweetService;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TweetService tweetService;

    @GetMapping
    public List<Tweet> getAll() {
        return tweetService.getAll();
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
                                                    @RequestParam(required = false) Integer pageNum,
                                                    @RequestParam(required = false) Integer pageSize,
                                                    @RequestParam Boolean useCustomQuery) {
        if (useCustomQuery) {
            return tweetService.findByUsernameUsingCustomQuery(username, pageNum, pageSize);
        }
        return tweetService.findByUsername(username, pageNum, pageSize);
    }

    @GetMapping(value = "/search")
    public List<Tweet> search(@RequestParam String text,
                              @RequestParam(required = false) Integer pageNum,
                              @RequestParam(required = false) Integer pageSize) {
        return tweetService.search(text, pageNum, pageSize);
    }
}
