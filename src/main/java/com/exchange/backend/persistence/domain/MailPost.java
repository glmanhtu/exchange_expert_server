package com.exchange.backend.persistence.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by greenlucky on 4/16/17.
 */
@Document(collection = "mail_post")
@EntityListeners(EntityListeners.class)
public class MailPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private String title;

    private String content;

    private boolean read = false;

    private String forUser;

    @CreatedBy
    private String fromUser;

    @CreatedDate
    private long createdDate;

    public MailPost() {
    }

    public MailPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public MailPost(String title, String content, String forUser) {
        this.title = title;
        this.content = content;
        this.forUser = forUser;
    }

    public MailPost(String title, String content, String forUser, boolean read) {
        this.title = title;
        this.content = content;
        this.forUser = forUser;
        this.read = read;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String toString() {
        return "MailPost{"
                + "id='" + id + '\''
                + ", title='" + title + '\''
                + ", content='" + content + '\''
                + ", read=" + read
                + ", forUser='" + forUser + '\''
                + ", createdDate=" + LocalDateTime.ofInstant(Instant.ofEpochMilli(createdDate), ZoneId.systemDefault())
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

        MailPost mailPost = (MailPost) o;

        return id != null ? id.equals(mailPost.id) : mailPost.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
