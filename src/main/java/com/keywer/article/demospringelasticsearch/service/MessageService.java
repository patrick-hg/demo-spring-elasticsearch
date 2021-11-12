package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.keywer.article.demospringelasticsearch.utils.Utils.pageableOf;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        List<Message> tweets = new ArrayList<>();
        messageRepository.findAll().forEach(tweets::add);
        return tweets;
    }

    public List<Message> findByUsername(String username, int pageNum, int pageSize) {
        return messageRepository.findByUsernameOrderByCreationDate(username, pageableOf(pageNum, pageSize))
                .getContent();
    }

    public List<Message> findByUsernameUsingCustomQuery(String username, Integer pageNum, Integer pageSize) {
        return messageRepository.findByUsernameUsingCustomQuery(username, pageableOf(pageNum, pageSize))
                .getContent();
    }

    public List<Message> search(String text, Integer pageNum, Integer pageSize) {
        return messageRepository.search(text, pageableOf(pageNum, pageSize))
                .getContent();
    }
}
