package com.exchange.backend.persistence.domain;

/**
 * Created by optimize on 3/19/17.
 */
public class Feedback extends Comment {

    private String title;


    public Feedback() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
