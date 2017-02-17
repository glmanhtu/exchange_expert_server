package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeConverter;
import org.joda.time.LocalDateTime;

import javax.persistence.Convert;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by greenlucky on 1/24/17.
 */
public class Content {

    private String by;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime on;

    @Min(value = 1)
    @Max(value = 5)
    private double value = 1.0;

    public Content() {
    }

    public Content(String by, LocalDateTime on, double value) {
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

    public LocalDateTime getOn() {
        return on;
    }

    public void setOn(LocalDateTime on) {
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

}
