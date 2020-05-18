package com.keywer.article.demospringelasticsearch.model;

import com.keywer.article.demospringelasticsearch.utils.Utils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "tweets", type = "tweet")
public class Tweet {

    @Id
    private String id;
    private String content;
    private List<String> tags;
    private String localization;
    private Boolean available;
    private Date creationDate;
    private String username;

    protected Tweet() {
    }

    public Tweet(String localization, String username, String content, Date creationDate) {
        this.localization = localization;
        this.username = username;
        this.content = content;
        this.creationDate = creationDate;
        this.available = true;
        this.tags = Utils.tagsFromText(content);
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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
}
