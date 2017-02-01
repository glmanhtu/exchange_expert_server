package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Type {

    private String name;

    private String description;

    public Type() {
    }

    public Type(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Type{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
