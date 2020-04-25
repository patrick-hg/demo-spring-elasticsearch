package com.keywer.article.demospringelasticsearch.dao;

import com.keywer.article.demospringelasticsearch.model.Personne;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends ElasticsearchRepository<Personne, String> {
}
