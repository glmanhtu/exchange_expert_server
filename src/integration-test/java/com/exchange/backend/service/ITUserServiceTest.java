package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by greenlucky on 4/15/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITUserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void init() throws Exception{
        userService.delete("nguyenlamit86@gmail.com");
    }

    @Test
    public void getOne() throws Exception {
        User user = userService.getOne("nguyenlamit86@gmail.com");
        System.out.println(user.toString());

    }

    @Test
    public void createOne() throws Exception {
        User user = new User();
        user.setId("nguyenlamit86@gmail.com");
        user.setFirstName("Lam");
        user.setLastName("Nguyen");
        user.setPassword("123456");

        user = userService.create(user);
        System.out.println(user.toString());
    }

}