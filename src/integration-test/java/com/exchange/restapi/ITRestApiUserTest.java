package com.exchange.restapi;

import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.UserService;
import com.exchange.restapi.userhandle.RestApiUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITRestApiUserTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void getUserInfo(){

        User user = userService.getOne("member_1@gmail.com");
        Assert.assertNotNull(user);
        Object body = this.testRestTemplate.getForObject("/user/member_1@gmail.com", RestApiUser.class);
        //assertThat(body).isEqualTo(user);
    }
}
