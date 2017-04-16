package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleResponse {

    private SafeSearchAnnotation safeSearchAnnotation;

    public GoogleResponse() {
    }

    public GoogleResponse(SafeSearchAnnotation safeSearchAnnotation) {
        this.safeSearchAnnotation = safeSearchAnnotation;
    }

    public SafeSearchAnnotation getSafeSearchAnnotation() {
        return safeSearchAnnotation;
    }

    public void setSafeSearchAnnotation(SafeSearchAnnotation safeSearchAnnotation) {
        this.safeSearchAnnotation = safeSearchAnnotation;
    }

}
