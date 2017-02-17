package com.exchange.backend.persistence.domain;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by optimize on 2/7/17.
 */
@Document(indexName = "good", type = "good", shards = 1)
public class ElasticGood implements Serializable {

    /**
     * The Id of this Good.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "post_date")
    private Long postDate;

    /**
     * The locations can be trading this goods.
     */
    private Location location;

    /**
     * Gets id of this goods.
     * @return Id of this goods.
     */
    public final String getId() {
        return id;
    }

    /**
     * Sets id given by id.
     * @param id of this goods with style is long.
     */
    public final void setId(String id) {
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
     * @return The postDate with format Date.getTime()
     */
    public final Long getPostDate() {
        return postDate;
    }

    /**
     * Sets postDate given by postDate.
     * @param postDate The variable input with Date.getTime() format
     */
    public final void setPostDate(final Long postDate) {
        this.postDate = postDate;
    }

    /**
     * Gets location of this goods.
     * @return A location
     * @see Location
     */
    public final Location getLocation() {
        return location;
    }

    /**
     * Set location
     * @param location The variable input
     */
    public final void setLocation(final Location location) {
        this.location = location;
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
        if (!(o instanceof ElasticGood)) {
            return false;
        }

        ElasticGood that = (ElasticGood) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ElasticGood{"
                + "id='" + id + '\''
                + ", title='" + title + '\''
                + ", slug='" + slug + '\''
                + ", description='" + description + '\''
                + ", type=" + type
                + ", price=" + price
                + ", postBy=" + postBy
                + ", postDate=" + postDate
                + ", location=" + location
                + '}';
    }
}
