package com.keywer.article.demospringelasticsearch.dao;

import com.keywer.article.demospringelasticsearch.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {

    Page<Tweet> findByUsernameOrderByCreationDate(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"username\": \"?0\"}}]}}")
    Page<Tweet> findByUsernameUsingCustomQuery(String name, Pageable pageable); // elasticsearch boolean query
}
