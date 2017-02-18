package com.exchange.backend.persistence.dto;

import com.exchange.backend.persistence.domain.Location;
import com.exchange.backend.persistence.domain.Category;

/**
 * Created by glmanhtu on 2/16/17.
 */
public class ElasticGoodDto {
    private String id;

    /**
     * The title of this Good.
     */
    private String title;

    /**
     * The slug to replace for id to get this good.
     * Which has a style is abc-def-kcl, typicaly which will
     * create from title.
     */
    private String slug;

    /**
     * The brief of this good.
     */
    private String description;

    /**
     * The type of this good.
     */
    private Category type;

    /**
     * The price of this good.
     */
    private double price = 0.0;

    private UserDto seller;

    private Long postDate;

    /**
     * The locations can be trading this goods.
     */
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
