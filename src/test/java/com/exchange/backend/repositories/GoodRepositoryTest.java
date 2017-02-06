package com.exchange.backend.repositories;

import com.exchange.ExchangeApplication;
import com.exchange.backend.persistence.domain.*;
import com.exchange.backend.persistence.repositories.GoodRepository;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExchangeApplication.class)
public class GoodRepositoryTest {

    @Autowired
    private GoodRepository goodRepository;


    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() throws Exception{
        Assert.assertNotNull(goodRepository);
        createGood();
    }

    @Test
    public void create() throws Exception {

        Good aspectGood = goodRepository.findOne(Long.valueOf(1));

        Assert.assertNotNull(aspectGood);
    }

    @Test
    public void update() throws Exception{
        Good aspectGood = goodRepository.findOne(Long.valueOf(1));
        aspectGood.setFeatured(false);
        goodRepository.save(aspectGood);
        Assert.assertEquals("The featured of good must be false", false, aspectGood.isFeatured());
    }

    @Test
    public void delete() throws Exception{
        goodRepository.delete(Long.valueOf(1));
        Good aspectGood = goodRepository.findOne(Long.valueOf(1));
        Assert.assertNull(aspectGood);
    }

    @Test
    public void addComment() throws Exception{
        Comment comment = createComment(testName.getMethodName(), null);
        Good good = goodRepository.findOne(Long.valueOf(1));
        good.setComments(Arrays.asList(comment));
        goodRepository.save(good);

        Good actualGood = goodRepository.findOne(Long.valueOf(1));
        Assert.assertEquals("The comment of good must be Arrays of comment", actualGood.getComments().size(), 1);
    }

    private void createGood(){

        Good good = new Good();
        good.setId(Long.valueOf(1));
        good.setTitle("This is a test of good");
        good.setSlug("this-is-a-test-of-good");
        good.setPostDate(LocalDateTime.now(Clock.systemDefaultZone()));
        good.setDescription("This is a test of good.");
        good.setContent("This is test content of the good, I just write sample text...");

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

        good = goodRepository.save(good);
    }

    private Comment createComment(String str, String commentBy){
        Comment comment = new Comment();
        comment.setBy(commentBy);
        comment.setCommentDate(LocalDateTime.now(Clock.systemDefaultZone()));
        comment.setMessage(str);
        return comment;
    }
}