package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.MailPost;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by greenlucky on 4/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITMailPostServiceTest {

    @Autowired
    private MailPostService mailPostService;

    private String user;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void init() throws Exception {

        //mailPostService.deleteAll();

        Assert.assertNotNull(mailPostService);
        user = "nguyenlamit86@gmail.com";

       for(int i = 0; i < 10; i++) {
            String title = testName.getMethodName() + i;
            String content = "This is content of test mail post" + i;
            System.out.println(title);
            if(i % 2 == 0) {
                mailPostService.create(title, content, user, true);
            } else {
                mailPostService.create(title, content, user, false);
            }
        }
    }

    @Test
    public void getMailPostOfUser() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<MailPost> mailPosts = mailPostService.getMailPostofUser(user, pageRequest);
        System.out.println(mailPosts.getContent());
    }

    @Test
    public void makeAsRead() throws Exception {
        MailPost mailPost = mailPostService.getOne("58f300198ea52f060c91acce");
        mailPost.setRead(true);
        mailPostService.makeAsRead(mailPost);
    }
}