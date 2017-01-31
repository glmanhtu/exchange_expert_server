package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Image {
    private String url;

    private String title;

    private String alt;

    public Image() {
    }

    public Image(String url, String title, String alt) {
        this.url = url;
        this.title = title;
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }
}
