package com.exchange.backend.persistence.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
public class Rating {

    private double avg = 0.0;

    private List<Content> contents = new ArrayList<>();

    public Rating() {

    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public List<Content> getContent() {
        return contents;
    }

    public void setContent(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Rating{"
                + "avg=" + avg
                + ", contents=" + contents
                + '}';
    }


}
