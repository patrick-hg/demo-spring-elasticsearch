package com.keywer.article.demospringelasticsearch.dao;

import com.keywer.article.demospringelasticsearch.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {

    Page<Message> findByUsernameOrderByCreationDate(String name, Pageable pageable);

    /*
    "query": {
        "bool": {
            "must": [
                {
                    "match": {
                        "username": "?0"
                    }
                }
            ]
        }
    }  */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"username\": \"?0\"}}]}}")
    Page<Message> findByUsernameUsingCustomQuery(String name, Pageable pageable); // elasticsearch boolean query

    /*
    "query": {
        "bool": {
            "should": [
                {
                    "match": {
                        "content": "?0"
                    }
                },
                {
                    "match": {
                        "username": "?0"
                    }
                },
                {
                    "match": {
                        "localization": "?0"
                    }
                }
            ]
        }
    }  */
    @Query("{\"bool\": {\"should\": [{\"match\": {\"content\": \"?0\"}}, {\"match\": {\"username\": \"?0\"}}, {\"match\": {\"localization\": \"?0\"}}]}}")
    Page<Message> search(String text, Pageable pageable);
}
