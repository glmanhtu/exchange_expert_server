package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.repositories.elasticsearch.ElasticGoodRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by optimize on 2/10/17.
 * Simple test CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITGoodServiceTest {

    @Autowired
    private GoodService goodService;

    @Autowired
    private ElasticGoodRepository elasticGoodRepository;

    @Test
    public void testGoodService() throws Exception {
        String goodId = "glmanhtu@gmail.com";
        String goodDescription = "Here is test good";
        Good good = new Good();
        good.setDescription(goodDescription);
        good.setId(goodId);
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

        List<Good> goods = goodService.getAll();
        for (Good good1 : goods) {
            ElasticGood elasticGood = new ElasticGood();
            elasticGood.setDescription(good1.getDescription());
            elasticGood.setId(good1.getId());
            elasticGood.setLocation(good1.getLocation());
            elasticGood.setPostBy(good1.getPostBy());
            elasticGood.setPostDate(good1.getPostDate());
            elasticGood.setPrice(good1.getPrice());
            elasticGood.setSlug(good1.getSlug());
            elasticGood.setTitle(good1.getTitle());
            elasticGood.setType(good1.getType());
            elasticGoodRepository.save(elasticGood);
        }
    }

    @Test
    public void testElasticSearch() throws Exception {
        String goodId = "glmanhtu@gmail.com";
        String goodDescription = "Here is test good";
        ElasticGood elasticGood = new ElasticGood();
        elasticGood.setId(goodId);
        elasticGood.setDescription(goodDescription);
        try {
            elasticGood = elasticGoodRepository.save(elasticGood);
        } catch (Exception e) {
            Assert.assertTrue("Save good failed, message: " + e.getMessage(), false);
        }

        try {
            elasticGood = elasticGoodRepository.findOne(goodId);
            Assert.assertNotNull("Get good success", elasticGood);
            Assert.assertEquals("Verify content of good", elasticGood.getDescription(), goodDescription);
        } catch (Exception e) {
            Assert.assertTrue("Get good failed" + e.getMessage(), false);
        }

        String updatedValue = "abc";
        elasticGood.setDescription(updatedValue);
        elasticGoodRepository.save(elasticGood);
        elasticGood = elasticGoodRepository.findOne(goodId);
        Assert.assertEquals("Test update", elasticGood.getDescription(), updatedValue);

        try {
            elasticGoodRepository.delete(goodId);
            Assert.assertNotNull("Delete good success", elasticGood);
        } catch (Exception e) {
            Assert.assertTrue("Delete good failed" + e.getMessage(), false);
        }
    }

}