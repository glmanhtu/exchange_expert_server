package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.joda.time.LocalDateTime;

import javax.persistence.Convert;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Comment {

    private String message;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime commentDate;

    private String by;

    public Comment() {
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

    @Override
    public String toString() {
        return "Comment{"
                + "message='" + message + '\''
                + ", commentDate=" + commentDate
                + ", by='" + by + '\''
                + '}';
    }
}
