package com.exchange.backend.persistence.dto;

import com.exchange.backend.persistence.domain.Category;
import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

/**
 * Created by glmanhtu on 2/16/17.
 */
public class SimpleGoodDto {

    private String id;

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

    private Category category;

    private double price = 0.0;

    private UserDto seller;

    private Long postDate;

    private List<GeoPoint> location;

    private String featuredImage;

    public SimpleGoodDto() {

    }

    public SimpleGoodDto(Good good) {

        this.id = good.getId();
        this.title = good.getTitle();
        this.slug = good.getSlug();
        this.description = good.getDescription();
        this.category = good.getCategory();
        this.price = good.getPrice();
        this.seller = (good.getPostBy() != null) ? new UserDto(good.getPostBy()) : null;
        this.postDate = good.getPostDate();
        this.location = good.getLocation();
        setFeaturedImage(good.getFeaturedImage());

    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<GeoPoint> getLocation() {
        return location;
    }

    public void setLocation(List<GeoPoint> location) {
        this.location = location;
    }
}
