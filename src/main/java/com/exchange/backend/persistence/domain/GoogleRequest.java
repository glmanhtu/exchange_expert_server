package com.exchange.backend.persistence.domain;


import java.util.Arrays;
import java.util.List;

/**
 * Created by greenlucky on 4/14/17.
 */
public class GoogleRequest {

    private GoogleImage image;
    private List<GoogleFeature> features = null;

    public GoogleRequest() {
    }

    public GoogleRequest(GoogleImage image, GoogleFeature feature) {
        this.image = image;
        this.features = Arrays.asList(feature);
    }

    public GoogleImage getImage() {
        return image;
    }

    public void setImage(GoogleImage image) {
        this.image = image;
    }

    public List<GoogleFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GoogleFeature> features) {
        this.features = features;
    }
}



