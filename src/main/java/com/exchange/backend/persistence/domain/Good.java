package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Document(collection = "goods")
public class Good extends ElasticGood implements Serializable {

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
    @Column(name = "featured_image")
    private String featuredImage;

    /**
     * The time to publish this good.
     */
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    /**
     * The status of this good.
     */
    private Status status;

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


}
