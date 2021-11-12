package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.MessageRepository;
import com.keywer.article.demospringelasticsearch.dao.UserRepository;
import com.keywer.article.demospringelasticsearch.model.Message;
import com.keywer.article.demospringelasticsearch.model.User;
import com.keywer.article.demospringelasticsearch.utils.Utils;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ImportFromCSV {

    private final Logger LOGGER = LoggerFactory.getLogger(ImportFromCSV.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/import-data")
    public void importData() {
        importUsers();
        importMessages();
    }

    private void importUsers() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/users.csv"), ';');
            String[] line;
            reader.skip(1);
            List<User> users = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = reader.readNext()) != null) {
                users.add(new User(line[0].trim(), line[1].trim(), line[2].trim(), dateFormat.parse(line[3].trim())));
            }
            userRepository.saveAll(users);
            LOGGER.info("{} users ont été importés avec succès", users.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void importMessages() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/messages.csv"), ';');
            String[] line;
            reader.skip(1);
            List<Message> messages = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                messages.add(new Message(line[0].trim(), line[1].trim(), line[2].trim(), Utils.randomDateInRange(365)));
            }
            messageRepository.saveAll(messages);
            LOGGER.info("{} posts ont été importés avec succès", messages.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
