package com.exchange.backend.persistence.domain;

import com.exchange.backend.enums.MessageEnum;

import java.io.Serializable;

/**
 * Created by greenlucky on 12/21/16.
 */
public class Message implements Serializable {

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private String link;


    public Message() {
    }

    public Message(MessageEnum massage) {
        this.code = massage.getCode();
        this.message = massage.getMessage();
        this.link = massage.getLink();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }
}
