package com.exchange.backend.persistence.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by greenlucky on 1/24/17.
 */
public class Content {

    private String by;

    private Long on;

    @Min(value = 1)
    @Max(value = 5)
    private double value = 1.0;

    public Content() {
    }

    public Content(String by, Long on, double value) {
        this.by = by;
        this.on = on;
        this.value = value;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Long getOn() {
        return on;
    }

    public void setOn(Long on) {
        this.on = on;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Content{"
                + "by='" + by + '\''
                + ", on=" + on
                + ", value=" + value
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

        Content content = (Content) o;

        return by != null ? by.equals(content.by) : content.by == null;
    }

    @Override
    public int hashCode() {
        return by != null ? by.hashCode() : 0;
    }
}
