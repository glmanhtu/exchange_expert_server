package com.exchange.backend.persistence.domain;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by optimize on 2/7/17.
 */
@Document(indexName = "good", type = "good", shards = 1)
public class ElasticGood implements Serializable {

    @Id
    private String id;

    private String title;

    /**
     * The slug to replace for id to get this good.
     * Which has a style is abc-def-kcl, typicaly which will
     * create from title.
     */
    private String slug;

    private String description;

    private Category category;

    private double price = 0.0;


    /**
     * The status of this good.
     */
    private Status status;

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

    private Location location;

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(final String title) {
        this.title = title;
    }


    public final String getSlug() {
        return slug;
    }

    public final void setSlug(final String slug) {
        this.slug = slug;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public final double getPrice() {
        return price;
    }

    public final void setPrice(final double price) {
        this.price = price;
    }

    public final Long getPostDate() {
        return postDate;
    }

    public final void setPostDate(final Long postDate) {
        this.postDate = postDate;
    }

    public final Location getLocation() {
        return location;
    }

    public final void setLocation(final Location location) {
        this.location = location;
    }

    public final User getPostBy() {
        return postBy;
    }

    public final void setPostBy(final User postBy) {
        this.postBy = postBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                + ", category=" + category
                + ", price=" + price
                + ", postBy=" + postBy
                + ", postDate=" + postDate
                + ", location=" + location
                + '}';
    }
}
