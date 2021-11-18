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
        "term": {
            "username": "?0"
        }
    }   */
    @Query("{\"term\": {\"username\": \"?0\"}}")
    Page<Message> findByUsernameUsingCustomQuery(String name, Pageable pageable); // elasticsearch term query

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
                },
                {
                    "match": {
                        "language": "?0"
                    }
                }
            ]
        }
    }  */
    @Query("{\"bool\": {\"should\": [{\"match\": {\"content\": \"?0\"}}, {\"match\": {\"username\": \"?0\"}}, {\"match\": {\"localization\": \"?0\"}}, {\"match\": {\"language\": \"?0\"}}]}}")
    Page<Message> search(String text, Pageable pageable);

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
                },
                {
                    "match": {
                        "language": "?0"
                    }
                }
            ],
            "must": {
                "range": {
                    "creationDate": { "gte": "2020-01-01", "lte": "2022-01-01"}
                }
            }
        }
    }  */
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"content\": \"?0\"}}, {\"match\": {\"username\": \"?0\"}}, {\"match\": {\"localization\": \"?0\"}}, {\"match\": {\"language\": \"?0\"}}, {\"range\": {\"creationDate\": {\"gte\": \"2015-01-01\", \"lt\": \"2022-01-01\", \"format\": \"yyyy-MM-dd\"}}}]}}")
    @Query("{\"range\": {\"creationDate\": {\"gte\": \"20190101\", \"lt\": \"20200101\", \"format\": \"yyyy-MM-dd\"}}}")
    Page<Message> searchWithDateRange(String text, String from, String until, Pageable pageable);
}
