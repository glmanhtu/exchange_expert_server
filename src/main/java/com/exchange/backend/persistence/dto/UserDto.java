package com.exchange.backend.persistence.dto;

import com.exchange.backend.persistence.domain.User;

/**
 * Created by glmanhtu on 2/16/17.
 */
public class UserDto {

    private String id;

    private String firstName;

    private String lastName;

    private long birthday;

    private String avatar;

    private long createDate;

    private int gender = 1;

    private double avgRating;

    public UserDto() { }

    public UserDto(String userId) {
        this.id = userId;
    }

    public UserDto(User user) {

        if(user.getId() != null)
            this.id = user.getId();
        if(user.getFirstName() != null)
            this.firstName = user.getFirstName();
        if(user.getLastName() != null)
            this.lastName = user.getLastName();
        if(user.getBirthday() != null)
            this.birthday = user.getBirthday();
        if(user.getAvatar() != null)
            this.avatar = user.getAvatar();
        if(user.getCreateDate() != null)
            this.createDate = user.getCreateDate();
        if(user.getRating() != null)
            this.avgRating = user.getRating().getAvg();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}

