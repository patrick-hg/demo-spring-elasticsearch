package com.keywer.article.demospringelasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "users", type = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String name;
    private String nationality;
    private Date memberSince;

    protected User() {
    }

    public User(String username, String name, String nationality, Date memberSince) {
        this.username = username;
        this.name = name;
        this.nationality = nationality;
        this.memberSince = memberSince;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
}
