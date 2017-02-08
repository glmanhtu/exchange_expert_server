package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by optimize on 2/7/17.
 */
public class ElasticGood implements Serializable {
    /**
     * The Id of this Good.
     */
    @Id
    private long id;

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
    private Type type;

    /**
     * The price of this good.
     */
    private double price = 0.0;

    /**
     * The user who post this good.
     */
    @DBRef
    @Column(name = "post_by")
    private User postBy;

    /**
     * The time to create this good.
     * This method using LocalDateTimeConverter to
     * convert from LocalDateTimeConverter to Timestamp
     */
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "post_date")
    private LocalDateTime postDate;

    /**
     * The locations can be trading this goods.
     */
    private List<Location> locations = new ArrayList<>();

    /**
     * Gets id of this goods.
     * @return Id of this goods.
     */
    public final long getId() {
        return id;
    }

    /**
     * Sets id given by id.
     * @param id of this goods with style is long.
     */
    public final void setId(long id) {
        this.id = id;
    }

    /**
     * Gets title of this good.
     * @return The title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Sets title.
     * @param title of this good.
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return The slug of this good.
     */
    public final String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     * @param slug the variable input to slug
     */
    public final void setSlug(final String slug) {
        this.slug = slug;
    }

    /**
     * Gets description.
     * @return The description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets description.
     * @param description the variable input
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets type.
     * @return the type.
     * @see Type
     */
    public final Type getType() {
        return type;
    }

    /**
     * Sets type.
     * @param type the variable input
     * @see Type
     */
    public final void setType(final Type type) {
        this.type = type;
    }

    /**
     * Gets price of this goods.
     * @return the price
     */
    public final double getPrice() {
        return price;
    }

    /**
     * Sets price to this goods.
     * @param price the variable input
     */
    public final void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Gets postDate of this goods.
     * @return The postDate with format LocalDateTime
     * @see LocalDateTime
     */
    public final LocalDateTime getPostDate() {
        return postDate;
    }

    /**
     * Sets postDate given by postDate.
     * @param postDate The variable input with LocalDateTime format
     * @see LocalDateTime
     */
    public final void setPostDate(final LocalDateTime postDate) {
        this.postDate = postDate;
    }

    /**
     * Gets locations of this goods.
     * @return A list of location
     * @see Location
     */
    public final List<Location> getLocations() {
        return locations;
    }

    /**
     * Sets locations given by list of location.
     * @param locations The variable input
     */
    public final void setLocations(final List<Location> locations) {
        this.locations = locations;
    }

    /**
     * Gets PostBy who posted this goods.
     * @return postBy with type is User
     * @see User
     */
    public final User getPostBy() {
        return postBy;
    }

    /**
     * Sets postBy given by postBy.
     * @param postBy The variable input
     */
    public final void setPostBy(final User postBy) {
        this.postBy = postBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Good good = (Good) o;

        return getId() == good.getId();
    }
}
