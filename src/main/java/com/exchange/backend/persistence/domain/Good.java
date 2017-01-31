package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeAttributeConverter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by greenlucky on 1/24/17.
 */
@Document(collection = "goods")
public class Good {

    @Id
    private long id;

    private String title;

    private String slug;

    private String content;

    private String description;

    private Type type;

    private double price=0.0;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime postDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime publishDate;

    private Status status;

    private boolean featured=false;

    @DBRef
    @Column(name = "post_by")
    private User postBy;

    private Set<Image> images;

    private Set<Location> locations;

    private Set<Comment> comments;



}
