package com.exchange.backend.datatype;

/**
 * Created by glmanhtu on 2/15/17.
 */
public class Order {
    private String by;
    private Boolean isASC;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Boolean getASC() {
        return isASC;
    }

    public void setIsASC(Boolean isASC) {
        this.isASC = isASC;
    }
}
