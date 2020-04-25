package com.keywer.article.demospringelasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personne")
public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;  // adapte aux operations CRUD

    @Autowired
    private PersonneDao personneDao;                // adapte aux operations plus avances

    @GetMapping
    public List<Personne> getAll() {
        List<Personne> personnes = new ArrayList<>();
        personneRepository.findAll().forEach(personnes::add);
        return personnes;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Personne addPersonne(@RequestBody Personne personne) {
        personne.setCreationDate(new Date());
        return personneRepository.save(personne);
    }



}
