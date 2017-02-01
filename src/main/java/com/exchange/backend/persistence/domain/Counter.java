package com.exchange.backend.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by greenlucky on 1/19/17.
 */
@Document(collection = "counters")
public class Counter {

    @Id
    private String id;

    private int seq;

    public Counter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id='" + id + '\'' +
                ", seq=" + seq +
                '}';
    }
}
