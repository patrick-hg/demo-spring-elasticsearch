package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> findByUsername(String username, int pageNum, int pageSize) {
        return tweetRepository.findByUsernameOrderByCreationDate(username, PageRequest.of(pageNum, pageSize))
                .getContent();
    }

    public List<Tweet> findByUsernameUsingCustomQuery(String username, int pageNum, int pageSize) {
        return tweetRepository.findByUsernameUsingCustomQuery(username, PageRequest.of(pageNum, pageSize))
                .getContent();
    }
}
