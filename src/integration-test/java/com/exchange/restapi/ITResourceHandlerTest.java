package com.exchange.restapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by greenlucky on 4/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITResourceHandlerTest {

    @Autowired
    private ResourceHandler resourceHandler;


    @Before
    public void init() throws Exception {

    }

    @Test
    public void detectIllegalImage() throws Exception {
        String filePath = "/Users/greenlucky/Downloads/50608-40122.jpg";
        resourceHandler.detectIllegalImage(filePath);
    }

}