package com.exchange.backend.service;

import com.exchange.ExchangeApplication;
import com.exchange.backend.persistence.domain.By;
import com.exchange.backend.persistence.domain.Content;
import com.exchange.backend.persistence.domain.Rating;
import com.exchange.backend.persistence.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by greenlucky on 1/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExchangeApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() throws Exception{
        Assert.assertNotNull(userService);
        Assert.assertNotNull(testName);
    }

    @Test
    public void create() throws Exception {

        Set<String> roles = new HashSet<>();
        roles.add("ADMIN_ROLE");

        User user = new User();
        user.setId(testName.getMethodName()+"@gamil.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user.setRoles(roles);

        user = userService.create(user);
        System.out.println(user);

        By by = new By();
        by.setEmail(user.getId());
        by.setFirstName(user.getFirstName());
        by.setLastName(user.getLastName());

        Content content = new Content();
        content.setBy(by);
        content.setOn(LocalDateTime.now(Clock.systemDefaultZone()));
        content.setValue(2);

        Set<Content> contents = new HashSet<>();
        contents.add(content);
        Rating rating = new Rating();
        rating.setContent(contents);


        User actualUser = userService.getOne(user.getId());
        actualUser.setRating(rating);
        actualUser = userService.update(actualUser);
        System.out.println(actualUser);

        //create new user
        User user1 = new User();
        user1.setId(testName.getMethodName()+"@gmail.com");
        user1.setFirstName(testName.getMethodName()+"A");
        user1.setLastName(testName.getMethodName());
        user1.setBirthday(LocalDate.of(1986,05,21));
        user1.setGender(1);
        user1.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user1.setRoles(roles);

        user1 = userService.create(user);

        //update rating user
        Content content1 = new Content();
        By by1 = new By(user1.getId(), user1.getFirstName(), user1.getLastName());
        content1.setBy(by1);
        content1.setValue(5);
        content1.setOn(LocalDateTime.now(Clock.systemDefaultZone()));
        actualUser.updateRating(content1);
        userService.update(actualUser);

        actualUser = userService.getOne(actualUser.getId());
        System.out.println(actualUser);
    }

    @Test
    public void checkingDuplicatedId() throws  Exception{
        User user = new User();
        user.setId(testName.getMethodName()+"@gamil.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));

        user = userService.create(user);
        user = userService.create(user);
        List<User> users = userService.getAll();
        System.out.println(users);
    }

}