package com.keywer.article.demospringelasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends ElasticsearchRepository<Personne, String> {
}
