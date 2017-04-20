package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.repositories.elasticsearch.ElasticGoodRepository;
import com.exchange.backend.persistence.repositories.mongodb.GoodRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Pattern;

/**
 * Created by optimize on 2/10/17.
 * Simple test CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITGoodServiceTest {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ElasticGoodRepository elasticGoodRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGoodService() throws Exception {
        String goodId = "glmanhtu@gmail.com";
        String goodDescription = "Here is test good";
        Good good = new Good();
        good.setDescription(goodDescription);
        good.setId(goodId);

        good = goodService.create(good);

        good = goodService.getOne(goodId);
        Assert.assertNotNull("Get good success", good);
        Assert.assertEquals("Verify content of good", good.getDescription(), goodDescription);


        String updatedValue = "abc";
        good.setDescription(updatedValue);
        goodService.update(good);
        good = goodService.getOne(goodId);
        Assert.assertEquals("Test update", good.getDescription(), updatedValue);


        goodService.delete(goodId);
        Assert.assertNotNull("Delete good success", good);

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

    @Test
    public void getGoodsOfUser() throws Exception {
        String userId = "xuanthanh4286@gmail.com";
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Good> goods = goodService.getGoodsOfUser(userId, pageRequest);
        System.out.println(goods.getContent());
    }

    @Test
    public void getGoods() throws Exception {


    }

}