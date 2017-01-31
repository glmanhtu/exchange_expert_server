package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeAttributeConverter;

import javax.persistence.Convert;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Comment {

    @Id
    private long id;

    private String message;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime commentDate;

    private String by;

    private Set<Comment> relies;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Set<Comment> getRelies() {
        return relies;
    }

    public void setRelies(Set<Comment> relies) {
        this.relies = relies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
