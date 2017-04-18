package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.User;
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

    @Test
    public void getOne() throws Exception {
        User user = userService.getOne("xuanthanh4286@gmail.com");
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode("123456"));
        user = userService.update(user);
        System.out.println(user.toString());
    }

}