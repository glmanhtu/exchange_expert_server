package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 4/14/17.
 */
public class SafeSearchAnnotation {

    private String adult;
    private String spoof;
    private String medical;
    private String violence;

    public SafeSearchAnnotation() {
    }

    public SafeSearchAnnotation(String adult, String spoof, String medical, String violence) {
        this.adult = adult;
        this.spoof = spoof;
        this.medical = medical;
        this.violence = violence;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getSpoof() {
        return spoof;
    }

    public void setSpoof(String spoof) {
        this.spoof = spoof;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getViolence() {
        return violence;
    }

    public void setViolence(String violence) {
        this.violence = violence;
    }

}
