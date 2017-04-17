package com.exchange.backend.service;

import com.vividsolutions.jts.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by greenlucky on 4/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailPostServiceTest {

    @Autowired
    private MailPostService mailPostService;

    @Test
    public void getMailPostofUserUnread() throws Exception {
        int unread = mailPostService.getMailPostofUserUnread("mathews@yahoo.com");
        Assert.equals(1, unread);
    }

}