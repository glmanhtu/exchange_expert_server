package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleFeature {

    private String type;

    public GoogleFeature() {
    }

    public GoogleFeature(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
