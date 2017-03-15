package com.exchange.backend.persistence.domain;

import com.exchange.backend.Roles;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by greenlucky on 1/24/17.
 */
@Document(collection = "users")
public class User implements Serializable, UserDetails {

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;

    /**
     * Id or email of user
     */
    @Id
    @Email
    private String id;

    /**
     * Password
     */
    private String password;

    private String firstName;

    private String lastName;

    private Long birthday;

    private String avatar;

    private Long createDate;

    //default 1: male; 0: female
    private int gender = 1;

    private boolean enabled = true;

    private List<String> roles = new ArrayList<>();

    private Rating rating;

    public User() {
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(ur->authorities.add(new Authority(ur)));
        authorities.add(new Authority(Roles.LOGGED_USER));
        return authorities;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + id + '\''
                + ", password='" + password + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", birthday=" + birthday
                + ", avatar='" + avatar + '\''
                + ", createDate=" + createDate
                + ", gender=" + gender
                + ", enabled=" + enabled
                + ", roles=" + roles
                + ", rating=" + rating
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

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
