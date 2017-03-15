package com.exchange.restapi;

import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.GoodService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void getAllGoogle() throws Exception{
        List<Good> goods = goodService.getAll();
        System.out.println(goods);
    }

    @Test
    public void suggestGoods() throws Exception{

        String title = "The Fates";

        PageRequest pageRequest = new PageRequest(0, 5,
                new Sort(new Sort.Order(Sort.Direction.ASC, "title")));
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (title != null) {
            queryBuilder.must(QueryBuilders.matchQuery("title", title));
        }
        List<ElasticGood> elasticGoods = goodService.findGoodsByQuery(queryBuilder, pageRequest);

        for (ElasticGood item : elasticGoods){
            System.out.println(item);
        }
    }
}