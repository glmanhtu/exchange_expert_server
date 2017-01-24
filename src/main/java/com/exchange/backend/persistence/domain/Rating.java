package com.exchange.backend.persistence.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by greenlucky on 1/24/17.
 */
public class Rating {

    private double avg=0.0;

    private Set<Content> contents = new HashSet<>();

    public Rating() {

    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg += avg;
    }

    public Set<Content> getContent() {
        return contents;
    }

    public void setContent(Set<Content> contents) {
        this.contents = contents;
        for (Content content : contents)
            setAvg(content.getAvg());
    }

    public void update(Content content){
        Set<Content> contents = this.getContent();
        contents.add(content);

        //update avg of rating
        this.setAvg(content.getAvg());
        //set new contents to rating
        this.setContent(contents);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "avg=" + avg +
                ", contents=" + contents +
                '}';
    }
}
