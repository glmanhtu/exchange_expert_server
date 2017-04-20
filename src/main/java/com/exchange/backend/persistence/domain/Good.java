package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Document(collection = "goods")
@EntityListeners(EntityListeners.class)
public class Good extends ElasticGood implements Serializable {

    /**
     * The Serial Version UID for Serializable classes
     */
    private static final long serialVersionUID = 1L;

    /**
     * The featured image of this good.
     * Which is stored url of image.
     */
    @Column(name = "featured_image")
    private String featuredImage;

    /**
     * The time to publish this good.
     */
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private long getModifiedDate;


    /**
     * List of image of this good.
     */
    private List<Image> images = new ArrayList<>();

    /**
     * The comments of this goods.
     */
    private List<Comment> comments = new ArrayList<>();

    /**
     * The contacts of this goods.
     */
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Declares constructor of this good.
     */
    public Good() {
    }

    /**
     * Gets publishDate.
     *
     * @return The publishDate with LocalDateTime format.
     * @see LocalDateTime
     */
    public final LocalDateTime getPublishDate() {
        return publishDate;
    }

    /**
     * Sets publishDate.
     *
     * @param publishDate The variable input
     */
    public final void setPublishDate(final LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Gets list of images of this goods.
     *
     * @return A list of image or null if not exist.
     */
    public final List<Image> getImages() {
        return images;
    }

    /**
     * Sets image given by list image.
     *
     * @param images The variable input
     */
    public final void setImages(final List<Image> images) {
        this.images = images;
    }

    /**
     * Gets comments of this goods.
     *
     * @return A list of comment
     * @see Comment
     */
    public final List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comments given by list comments.
     *
     * @param comments the variable input
     * @see Comment
     */
    public final void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Gets contacts of this goods.
     *
     * @return A list of contact
     */
    public final List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts given by List of contacts
     *
     * @param contacts The variable input
     * @see Contact
     */
    public final void setContacts(final List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Gets featured image of this goods.
     *
     * @return Featured image store url image of this good.
     */
    public final String getFeaturedImage() {
        return featuredImage;
    }

    /**
     * Sets featured image given by featured image.
     *
     * @param featuredImage The variable input
     */
    public final void setFeaturedImage(final String featuredImage) {
        this.featuredImage = featuredImage;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public long getGetModifiedDate() {
        return getModifiedDate;
    }

    public void setGetModifiedDate(long getModifiedDate) {
        this.getModifiedDate = getModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Good)) {
            return false;
        }

        Good that = (Good) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
