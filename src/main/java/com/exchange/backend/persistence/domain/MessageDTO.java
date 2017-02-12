package com.exchange.backend.persistence.domain;

import com.exchange.backend.enums.MessageType;

import java.io.Serializable;

/**
 * Created by greenlucky on 12/21/16.
 */
public class MessageDTO implements Serializable{

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;

    private String message;

    private MessageType messageType;

    public MessageDTO() {
    }

    public MessageDTO(MessageType messageType, String massage) {
        this.message = massage;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String massage) {
        this.message = massage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
