package com.keywer.article.demospringelasticsearch.service;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import com.keywer.article.demospringelasticsearch.model.SearchResult;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.keywer.article.demospringelasticsearch.utils.Utils.pageableOf;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public List<Message> findByUsername(String username, Integer pageNum, Integer pageSize) {
        Page<Message> pageResult = messageRepository.findByUsernameOrderByCreationDate(username, pageableOf(pageNum, pageSize));
        return pageResult != null ? pageResult.getContent() : Collections.emptyList();
    }

    public List<Message> findByUsernameUsingCustomQuery(String username, Integer pageNum, Integer pageSize) {
        Page<Message> pageResult = messageRepository.findByUsernameUsingCustomQuery(username, pageableOf(pageNum, pageSize));
        return pageResult != null ? pageResult.getContent() : Collections.emptyList();
    }

    public SearchResult search(String text, String from, String until, Integer pageNum, Integer pageSize) {
        Page<Message> pageResult = Strings.isEmpty(from) && Strings.isEmpty(until)
                ? messageRepository.search(text, pageableOf(pageNum, pageSize))
                : messageRepository.searchWithDateRange(text, from, until, pageableOf(pageNum, pageSize));
        return new SearchResult(pageResult.getContent(), pageResult.getTotalElements(), pageResult.getTotalPages(), pageResult.getNumberOfElements());
    }
}
