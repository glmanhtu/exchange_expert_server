package com.exchange.backend.service;

import com.exchange.ExchangeApplication;
import com.exchange.backend.persistence.domain.*;
import com.exchange.utils.UserUtils;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExchangeApplication.class)
public class GoodServiceTest {

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() throws Exception{
        Assert.assertNotNull(goodService);
        Assert.assertNotNull(userService);
        createGood();
    }

    @Test
    public void create() throws Exception {

        Good aspectGood = goodService.getOne(1);

        Assert.assertNotNull(aspectGood);
    }

    @Test
    public void update() throws Exception{
        Good aspectGood = goodService.getOne(1);
        aspectGood.setFeatured(false);
        goodService.update(aspectGood);
        Assert.assertEquals("The featured of good must be false", false, aspectGood.isFeatured());
    }

    @Test
    public void addComment() throws Exception{
        commentService.addComment(1, "admin@gmail.com", "This is the first comment to good");
        Good aspectGood = goodService.getOne(1);

        Assert.assertEquals("Number of comment must be equals 1",aspectGood.getComments().size(),1);

        //update comment
        String msg = "This is updated comment to good";
        commentService.updateComment(1,0,msg);
        Good actualGood = goodService.getOne(1);
        Assert.assertEquals("Number of comment must be equals: "+msg,actualGood.getComments().get(0).getMessage(),msg);

        //delete comment
        commentService.deleteComment(1,0);
        Good deleteGood = goodService.getOne(1);
        Assert.assertEquals("The size of comment must be equals 0",0,deleteGood.getComments().size());
    }

    @Test
    public void delete() throws Exception{

    }

    private void createGood(){
        User user = UserUtils.createUser(testName.getMethodName());
        user = userService.create(user);

        Good good = new Good();
        good.setId(Long.valueOf(1));
        good.setTitle("This is a test of good");
        good.setSlug("this-is-a-test-of-good");
        good.setPostDate(LocalDateTime.now(Clock.systemDefaultZone()));
        good.setDescription("This is a test of good.");
        good.setContent("This is test content of the good, I just write sample text...");
        good.setPostBy(user);

        //Add location
        List<Location> locations = new ArrayList<>();
        Location location = new Location();
        location.setLat("@89394829843");
        location.setLng("@89394829843");
        locations.add(location);

        good.setLocations(locations);

        //Add contact
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact("0908080810", "172 Binh Duong 1, An Binh, Di An, Binh Duong");
        contacts.add(contact);

        good.setContacts(contacts);

        good = goodService.create(good);
    }

    private Comment createComment(String str, String commentBy){
        Comment comment = new Comment();
        comment.setBy(commentBy);
        comment.setCommentDate(LocalDateTime.now(Clock.systemDefaultZone()));
        comment.setMessage(str);
        return comment;
    }
}