package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Location {

    private Double lat;

    private Double lon;

    public Location() {
    }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{"
                + "lat='" + lat + '\''
                + ", lon='" + lon + '\''
                + '}';
    }
}
