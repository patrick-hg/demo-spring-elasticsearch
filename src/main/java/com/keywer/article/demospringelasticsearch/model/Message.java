package com.keywer.article.demospringelasticsearch.model;

import com.keywer.article.demospringelasticsearch.utils.Utils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "messages", type = "message")
public class Message {

    @Id
    private String id;

//    @Field(type = FieldType.Text)
    private String content;
    private List<String> tags;
    private String localization;
    private Boolean available;

    @Field(type = FieldType.Date)
    private String creationDate;
    private String username;
    private String language;

    protected Message() {
    }

    public Message(String localization, String username, String content, String language, String creationDate) {
        this.localization = localization;
        this.username = username;
        this.content = content;
        this.creationDate = creationDate;
        this.available = true;
        this.language = language;
        this.tags = Utils.tagsFromText(content);
    }

    // Getters & setters ...
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
