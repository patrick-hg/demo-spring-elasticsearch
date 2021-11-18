package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import com.keywer.article.demospringelasticsearch.model.User;
import com.keywer.article.demospringelasticsearch.service.UserService;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ImportFromCSV {

    private final Logger LOGGER = LoggerFactory.getLogger(ImportFromCSV.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/import-data/{duplication}")
    public void importData(@PathVariable int duplication) {
        importUsers();
        importMessages(duplication);
    }

    private void importUsers() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/users.csv"), ';');
            String[] line;
            reader.skip(1);
            List<User> users = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = reader.readNext()) != null) {
                users.add(new User(line[0].trim(), line[1].trim(), Integer.parseInt(line[2].trim()), line[3].trim(), dateFormat.parse(line[4].trim())));
            }
            userService.persistAll(users);

            LOGGER.info("{} users ont été importés avec succès", users.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void importMessages(int duplication) {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/messages.csv"), ';');
            String[] line;
            reader.skip(1);
            List<Message> messages = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                messages.addAll(messagesFromLine(line, duplication));
            }
            messageRepository.saveAll(messages);
            LOGGER.info("{} posts ont été importés avec succès", messages.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Message> messagesFromLine(String[] line, int duplication) {
        List<Message> messages = new ArrayList<>();
        for (int i = -1; i < duplication; i++) {
            messages.add(parseMessage(line));
        }
        return messages;
    }

    private Message parseMessage(String[] line) {
        return new Message(line[0].trim(), line[1].trim(), line[3].trim(), line[2].trim(), Utils.randomDateInRange(365 * 5));
    }
}
