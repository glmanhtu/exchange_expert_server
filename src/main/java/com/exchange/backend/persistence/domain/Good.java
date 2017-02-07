package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Document(collection = "goods")
public class Good implements Serializable {

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;
    private static final short NUMBER_HASH = 32;

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
     * The featured image of this good.
     * Which is stored url of image.
     */
    private String featuredImage;

    /**
     * The time to create this good.
     * This method using LocalDateTimeConverter to
     * convert from LocalDateTimeConverter to Timestamp
     */
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime postDate;

    /**
     * The time to publish this good.
     */
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime publishDate;

    /**
     * The status of this good.
     */
    private Status status;

    /**
     * The variable whether the good shows in the home page.
     */
    private boolean featured = false;

    /**
     * The user who post this good.
     */
    @DBRef
    @Column(name = "post_by")
    private User postBy;

    /**
     * List of image of this good.
     */
    private List<Image> images = new ArrayList<>();

    /**
     * The locations can be trading this goods.
     */
    private List<Location> locations = new ArrayList<>();

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
     * Gets publishDate.
     * @return The publishDate with LocalDateTime format.
     * @see LocalDateTime
     */
    public final LocalDateTime getPublishDate() {
        return publishDate;
    }

    /**
     * Sets publishDate.
     * @param publishDate The variable input
     */
    public final void setPublishDate(final LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Gets status.
     * @return The status with format is Status
     * @see Status
     */
    public final Status getStatus() {
        return status;
    }

    /**
     * Sets status given by status
     * @param status The variable input with status formation
     * @see Status
     */
    public final void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * Gets featured of this goods.
     * @return boolean value.
     */
    public final boolean isFeatured() {
        return featured;
    }

    /**
     * Sets featured given by featured.
     * @param featured The variable input with boolean value
     */
    public final void setFeatured(final boolean featured) {
        this.featured = featured;
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

    /**
     * Gets list of images of this goods.
     * @return A list of image or null if not exist.
     */
    public final List<Image> getImages() {
        return images;
    }

    /**
     * Sets image given by list image.
     * @param images The variable input
     */
    public final void setImages(final List<Image> images) {
        this.images = images;
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
     * Gets comments of this goods.
     * @return A list of comment
     * @see Comment
     */
    public final List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comments given by list comments.
     * @param comments the variable input
     *                 @see Comment
     */
    public final void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Gets contacts of this goods.
     * @return A list of contact
     */
    public final List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts given by List of contacts
     * @param contacts The variable input
     * @see Contact
     */
    public final void setContacts(final List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Gets featured image of this goods.
     * @return Featured image store url image of this good.
     */
    public final String getFeaturedImage() {
        return featuredImage;
    }

    /**
     * Sets featured image given by featured image.
     * @param featuredImage The variable input
     */
    public final void setFeaturedImage(final String featuredImage) {
        this.featuredImage = featuredImage;
    }

    @Override
    public final String toString() {
        return "Good{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", slug='" + slug + '\''
                + ", description='" + description + '\''
                + ", type=" + type
                + ", price=" + price
                + ", featuredImage='" + featuredImage + '\''
                + ", postDate=" + postDate
                + ", publishDate=" + publishDate
                + ", status=" + status
                + ", featured=" + featured
                + ", postBy=" + postBy
                + ", images=" + images
                + ", locations=" + locations
                + ", comments=" + comments
                + ", contacts=" + contacts
                + '}';
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

        return id == good.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> NUMBER_HASH));
    }
}
