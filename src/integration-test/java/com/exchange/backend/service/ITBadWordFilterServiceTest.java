package com.exchange.backend.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by greenlucky on 3/14/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITBadWordFilterServiceTest {

    @Before
    public void init() throws Exception{

    }

    @Test
    public void filter() throws Exception{
        String string = "fuck you";
        String result = BadWordFilterService.filterText(string);
        System.out.println(result);
    }
}
