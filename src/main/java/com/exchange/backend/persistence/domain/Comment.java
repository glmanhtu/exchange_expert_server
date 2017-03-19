package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Comment {

    private String message;

    private Long commentDate;

    private String by;

    public Comment() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Long commentDate) {
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
