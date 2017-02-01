package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeAttributeConverter;
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
public class Good implements Serializable{

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String title;

    private String slug;

    private String content;

    private String description;

    private Type type;

    private double price=0.0;

    private String featuredImage;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime postDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime publishDate;

    private Status status;

    private boolean featured=false;

    @DBRef
    @Column(name = "post_by")
    private User postBy;

    private List<Image> images = new ArrayList<>();

    private List<Location> locations = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    private List<Contact> contacts = new ArrayList<>();

    public Good() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public User getPostBy() {
        return postBy;
    }

    public void setPostBy(User postBy) {
        this.postBy = postBy;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", featuredImage='" + featuredImage + '\'' +
                ", postDate=" + postDate +
                ", publishDate=" + publishDate +
                ", status=" + status +
                ", featured=" + featured +
                ", postBy=" + postBy +
                ", images=" + images +
                ", locations=" + locations +
                ", comments=" + comments +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        return id == good.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
