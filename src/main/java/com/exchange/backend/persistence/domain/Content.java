package com.exchange.backend.persistence.domain;

import com.exchange.backend.persistence.converter.LocalDateTimeAttributeConverter;

import javax.persistence.Convert;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * Created by greenlucky on 1/24/17.
 */
public class Content {
    private By by;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime on;

    @Min(value = 1)
    @Max(value = 5)
    private int value = 1;

    public Content() {
    }

    public Content(By by, LocalDateTime on, int value) {
        this.by = by;
        this.on = on;
        this.value = value;
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public LocalDateTime getOn() {
        return on;
    }

    public void setOn(LocalDateTime on) {
        this.on = on;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getAvg(){
        return Double.valueOf(value)/5;
    }

    @Override
    public String toString() {
        return "Content{" +
                "by=" + by +
                ", on=" + on +
                ", value=" + value +
                '}';
    }
}
