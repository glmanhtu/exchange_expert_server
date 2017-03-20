package com.exchange.restapi.request;

/**
 * Created by optimize on 3/21/17.
 */
public class GoodStatus {
    private String goodId;
    private int status;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
