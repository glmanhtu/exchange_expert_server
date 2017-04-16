package com.exchange.backend.enums;

/**
 * Created by greenlucky on 12/21/16.
 */
public enum MessageEnum {

    USER_NOT_FOUND(404, "User {0} was not found", ""),
    USER_EMAIL_INVALID(400, "User email {0} is invalid", ""),

    GOODS_NOT_FOUND(404, "Goods {0} was not found", ""),
    GOODS_SLUG_INVALID(400, "The slug {0} is invalid", ""),

    BAD_WORD_TITLE(403, "The title can not contain {0}", ""),
    BAD_WORD_DES(403, "The description can not contain {0}", ""),

    ACCESST_TOKEN_NULL(402, "Access token must be not null", ""),

    ACCESST_TOKEN_INVALID(401, "Access token was invalid", ""),

    IMAGE_CONTAINTS_ADULT(303, "Image contains adult content", ""),
    IMAGE_CONTAINTS_VIOLENCE(303, "Image contains violent content", ""),
    IMAGE_CONTAINTS_MEDICAL(303, "Image contains medical content", ""),
    MAIL_POST_NOT_FOUND(404, "Mail post id {0} not found", "");

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
