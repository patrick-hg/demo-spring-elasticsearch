package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import com.keywer.article.demospringelasticsearch.model.SearchResult;
import com.keywer.article.demospringelasticsearch.service.MessageService;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Message persist(@RequestBody Message message) {
        if (Strings.isEmpty(message.getId())) {
            message.setCreationDate(LocalDate.now().toString());
        }
        message.setTags(Utils.tagsFromText(message.getContent()));
        return messageRepository.save(message);
    }

    @GetMapping(value = "/find-by-username")
    public List<Message> findByUsernameWithPagination(@RequestParam String username,
                                                      @RequestParam(required = false) Integer pageNum,
                                                      @RequestParam(required = false) Integer pageSize,
                                                      @RequestParam Boolean useCustomQuery) {
        if (useCustomQuery) {
            return messageService.findByUsernameUsingCustomQuery(username, pageNum, pageSize);
        }
        return messageService.findByUsername(username, pageNum, pageSize);
    }

    @GetMapping(value = "/search")
    public SearchResult search(@RequestParam String text,
                               @RequestParam(required = false) Integer pageNum,
                               @RequestParam(required = false) Integer pageSize,
                               @RequestParam(required = false) String from,
                               @RequestParam(required = false) String until) {
        return messageService.search(text, from, until, pageNum, pageSize);
    }
}
