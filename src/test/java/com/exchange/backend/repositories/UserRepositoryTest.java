package com.exchange.backend.repositories;

import com.exchange.ExchangeApplication;
import com.exchange.backend.persistence.domain.Content;
import com.exchange.backend.persistence.domain.Rating;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.repositories.UserRepository;
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
import java.util.*;

/**
 * Created by greenlucky on 1/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExchangeApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() throws Exception{
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(testName);
    }

    @Test
    public void create() throws Exception {


        User user = new User();
        user.setId(testName.getMethodName()+"@gamil.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user.setRoles(Arrays.asList("ADMIN_ROLE"));

        user = userRepository.save(user);

        User actualUser = userRepository.findOne(user.getId());

        Assert.assertNotNull(actualUser);
        Assert.assertEquals("Actual user must be equals user", actualUser, user);

        //create new user
        User user1 = new User();
        user1.setId(testName.getMethodName()+"@gmail.com");
        user1.setFirstName(testName.getMethodName()+"A");
        user1.setLastName(testName.getMethodName());
        user1.setBirthday(LocalDate.of(1986,05,21));
        user1.setGender(1);
        user1.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user1.setRoles(Arrays.asList("ROLE_MEMBER"));

        user1 = userRepository.save(user);

        User actualUser1 = userRepository.findOne(user1.getId());

        Assert.assertNotNull(actualUser);
        Assert.assertEquals("Actual user 1 must be equals user", actualUser1, user1);

    }

    @Test
    public void updateUser() throws  Exception{
        User user = new User();
        user.setId(testName.getMethodName()+"@gmail.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));

        user = userRepository.save(user);

        User actualUser = userRepository.findOne(user.getId());

        Assert.assertNotNull(actualUser);

        String firstName = "John";
        //update user
        actualUser.setFirstName(firstName);
        actualUser = userRepository.save(actualUser);

        User actualUserUpdated = userRepository.findOne(actualUser.getId());

        Assert.assertEquals("First name of Actual user after updated must be quals John",
                actualUserUpdated.getFirstName(), firstName);

    }

    @Test
    public void deleteUser() throws  Exception{
        User user = new User();
        user.setId(testName.getMethodName()+"@gmail.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));

        user = userRepository.save(user);

        User actualUser = userRepository.findOne(user.getId());

        Assert.assertNotNull(actualUser);

        userRepository.delete(user.getId());


        User actualUserDeleted = userRepository.findOne(user.getId());

        Assert.assertNull(actualUserDeleted);
    }

    @Test
    public void addRating() throws Exception{
        Rating rating = new Rating();
        rating.setAvg(0);

        Content content = new Content();
        content.setBy("lamnguyen@gmail.com");
        content.setOn(LocalDateTime.now(Clock.systemDefaultZone()));
        content.setValue(5);

        Content content1 = new Content();
        content1.setBy("tucodepro@gmail.com");
        content1.setOn(LocalDateTime.now(Clock.systemDefaultZone()));
        content1.setValue(3);

        rating.setContent(Arrays.asList(content, content1));

        User user = new User();
        user.setId(testName.getMethodName()+"@gmail.com");
        user.setFirstName(testName.getMethodName());
        user.setLastName(testName.getMethodName());
        user.setBirthday(LocalDate.of(1986,05,20));
        user.setGender(1);
        user.setCreateDate(LocalDateTime.now(Clock.systemDefaultZone()));
        user.setRating(rating);
        user = userRepository.save(user);

        user = userRepository.save(user);

        User actualUser = userRepository.findOne(user.getId());

        Assert.assertNotNull(actualUser);
        Assert.assertEquals("Size of rating of actual user must be equals size of Rating", actualUser.getRating().getContent().size(), rating.getContent().size());
    }

}