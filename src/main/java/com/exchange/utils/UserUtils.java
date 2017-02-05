package com.exchange.utils;

import com.exchange.backend.persistence.domain.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by greenlucky on 1/31/17.
 */
public class UserUtils {

    private static final int YEAR = 1985;
    private static final int MONTH = 05;
    private static final int DAY = 20;

    private UserUtils() {
        throw new AssertionError("Not instantiable");
    }

    public static User createUser(String name) {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        User user = new User();
        user.setId(name + "@gmail.com");
        user.setFirstName(name);
        user.setLastName(name);
        user.setBirthday(LocalDate.of(YEAR, MONTH, DAY));
        user.setGender(1);
        user.setPassword("123456");
        user.setRoles(roles);
        return user;
    }
}
