package com.keywer.article.demospringelasticsearch.dao;

import com.keywer.article.demospringelasticsearch.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Repository;

@Repository
public class PersonneDaoImpl implements PersonneDao {

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.personne.type}")
    private String personneTypeName;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public Personne persistPersonne(Personne personne) {

        IndexQuery userQuery = new IndexQuery();
        userQuery.setIndexName(indexName);
        userQuery.setType(personneTypeName);
        userQuery.setObject(personne);

        esTemplate.refresh(indexName);

        return personne;
    }
}
