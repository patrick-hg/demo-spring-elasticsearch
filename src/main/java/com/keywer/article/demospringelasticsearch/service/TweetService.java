package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.TweetRepository;
import com.keywer.article.demospringelasticsearch.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> findByUsername(String username, int pageNum, int pageSize) {
        return tweetRepository.findByUsernameOrderByCreationDate(username, pageableOf(pageNum, pageSize))
                .getContent();
    }

    public List<Tweet> findByUsernameUsingCustomQuery(String username, Integer pageNum, Integer pageSize) {
        return tweetRepository.findByUsernameUsingCustomQuery(username, pageableOf(pageNum, pageSize))
                .getContent();
    }

    public List<Tweet> search(String text, Integer pageNum, Integer pageSize) {
        return tweetRepository.search(text, pageableOf(pageNum, pageSize))
                .getContent();
    }

    private Pageable pageableOf(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return Pageable.unpaged();
        }
        return PageRequest.of(pageNum, pageSize);
    }
}
