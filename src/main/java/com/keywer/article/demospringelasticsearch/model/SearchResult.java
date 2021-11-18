package com.keywer.article.demospringelasticsearch.model;

import java.util.List;

public class SearchResult {

    private long totalElements;
    private int totalPages;
    private int itemsPerPage;
    private List<Message> messages;

    public SearchResult(List<Message> messages, long totalElements, int totalPages, int itemsPerPage) {
        this.messages = messages;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.itemsPerPage = itemsPerPage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }
}
