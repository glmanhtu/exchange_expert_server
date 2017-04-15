package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleImage {

    private String content;

    public GoogleImage() {
    }

    public GoogleImage(String base64String) {
        this.content = base64String;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
