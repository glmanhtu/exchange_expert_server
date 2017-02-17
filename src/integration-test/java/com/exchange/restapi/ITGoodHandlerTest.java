package com.exchange.restapi;

import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.GoodService;
import com.github.slugify.Slugify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Mrs Hoang on 16/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITGoodHandlerTest {

    @Autowired
    private GoodService goodService;


    @Rule
    public TestName testName = new TestName();

    @Before
    public void init() throws Exception{
        Assert.assertNotNull(goodService);
    }

    @Test
    public void generationSlug() throws Exception{
        User user = new User();
        user.setId("member_1@gmail.com");

        Good good = new Good();
        good.setTitle("Chào mấy thím group exchange expert");
        good.setPostBy(user);

        good = goodService.create(good);
        System.out.println(good);
    }

}