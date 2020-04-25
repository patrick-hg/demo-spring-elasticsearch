package com.keywer.article.demospringelasticsearch.controller;

import com.keywer.article.demospringelasticsearch.dao.PublicationRepository;
import com.keywer.article.demospringelasticsearch.model.Publication;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @GetMapping
    public List<Publication> getAll() {
        List<Publication> publications = new ArrayList<>();
        publicationRepository.findAll().forEach(publications::add);
        return publications;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Publication persist(@RequestBody Publication publication) {
        if (Strings.isEmpty(publication.getId())) {
            publication.setCreationDate(new Date());
        }
        return publicationRepository.save(publication);
    }



}
