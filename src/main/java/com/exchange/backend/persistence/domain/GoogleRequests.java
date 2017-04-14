package com.exchange.backend.persistence.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleRequests {
    private List<GoogleRequest> requests = null;

    public GoogleRequests() {
        this.requests = new ArrayList<>();
    }

    public GoogleRequests(GoogleRequest requests) {
        this.requests = Arrays.asList(requests);
    }

    public List<GoogleRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<GoogleRequest> requests) {
        this.requests = requests;
    }
}
