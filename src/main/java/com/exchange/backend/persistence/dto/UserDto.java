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

    public UserDto(String userId){
        this.id = userId;
    }

    public UserDto(User user) {

        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthday = user.getBirthday();
        this.avatar = user.getAvatar();
        this.createDate = user.getCreateDate();
        this.gender = user.getGender();
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

