package com.exchange.backend.persistence.domain;

import java.util.List;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleResponses {

    private List<GoogleResponse> responses;

    public GoogleResponses() {
    }


    public GoogleResponses(List<GoogleResponse> responses) {
        this.responses = responses;
    }

    public List<GoogleResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<GoogleResponse> responses) {
        this.responses = responses;
    }

}
