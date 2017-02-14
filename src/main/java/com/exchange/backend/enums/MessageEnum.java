package com.exchange.backend.enums;

/**
 * Created by greenlucky on 12/21/16.
 */
public enum MessageEnum {

    USER_NOT_FOUND(404, "User was not found", ""),
    USER_EMAIL_INVALID(400, "User email is invalid", ""),

    GOODS_NOT_FOUND(404, "Goods was not found", ""),
    GOODS_SLUG_INVALID(400, "The slug is invalid", "");

    private int code;

    private String message;

    private String link;

    MessageEnum(int code, String message, String link) {
        this.code = code;
        this.message = message;
        this.link = link;
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
