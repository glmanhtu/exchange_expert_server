package com.exchange.backend.persistence.dto;

import com.exchange.backend.persistence.domain.Contact;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glmanhtu on 2/22/17.
 */
public class GoodDto extends SimpleGoodDto {
    private List<Image> images = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();

    public GoodDto(Good good) {
        super(good);
        setImages(good.getImages());
        setContacts(getContacts());
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
