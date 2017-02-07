package com.exchange.utils;

import com.exchange.backend.persistence.domain.User;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by Mrs Hoang on 08/02/2017.
 */
public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Not instantiable");
    }

    public static User createUser(String email, String name) {
        User user = new User();
        user.setId(email);
        user.setPassword("123456");
        user.setFirstName(name);
        user.setLastName(name);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user.setRoles(Arrays.asList("ADMIN_ROLE", "CUSTOMER_ROLE"));
        user.setAvatar("http://blog.florianlopes.io/wp-content/uploads/2016/04/spring-boot-project-logo.png");
        user.setEnabled(true);

        return user;
    }
}
