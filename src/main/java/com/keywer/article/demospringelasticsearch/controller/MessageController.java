package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import com.keywer.article.demospringelasticsearch.service.MessageService;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public Message persist(@RequestBody Message publication) {
        if (Strings.isEmpty(publication.getId())) {
            publication.setCreationDate(new Date());
        }
        publication.setTags(Utils.tagsFromText(publication.getContent()));
        return messageRepository.save(publication);
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
    public List<Message> search(@RequestParam String text,
                                @RequestParam(required = false) Integer pageNum,
                                @RequestParam(required = false) Integer pageSize) {
        return messageService.search(text, pageNum, pageSize);
    }
}
