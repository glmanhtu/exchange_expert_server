package com.exchange.backend.enums;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
public enum StatusEnum {

    PENDING("Pending", ""),
    TRADING("Trading", ""),
    BANNED("Banned", "");

    private String name;

    private String description;

    StatusEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static StatusEnum convert(int status) {
        switch (status) {
            case 0: return PENDING;
            case 1: return TRADING;
            default: return BANNED;
        }
    }
}
