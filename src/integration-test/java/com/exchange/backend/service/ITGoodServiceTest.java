package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by optimize on 2/10/17.
 * Simple test CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITGoodServiceTest {

    @Autowired
    private GoodService goodService;

    @Test
    public void testGoodService() throws Exception {
        String goodId = "glmanhtu@gmail.com";
        String goodDescription = "Here is test good";
        Good good = new Good();
        good.setDescription(goodDescription);
        good.setStatus(new Status("Pending", ""));
        good.setId(goodId);
        good.setFeaturedImage("asd");
        try {
            good = goodService.create(good);
        } catch (Exception e) {
            Assert.assertTrue("Save good failed, message: " + e.getMessage(), false);
        }

        try {
            good = goodService.getOne(goodId);
            Assert.assertNotNull("Get good success", good);
            Assert.assertEquals("Verify content of good", good.getDescription(), goodDescription);
        } catch (Exception e) {
            Assert.assertTrue("Get good failed" + e.getMessage(), false);
        }

        String updatedValue = "abc";
        good.setDescription(updatedValue);
        goodService.update(good);
        good = goodService.getOne(goodId);
        Assert.assertEquals("Test update", good.getDescription(), updatedValue);

        try {
            goodService.delete(goodId);
            Assert.assertNotNull("Delete good success", good);
        } catch (Exception e) {
            Assert.assertTrue("Delete good failed" + e.getMessage(), false);
        }
    }

}