package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.GoogleResponses;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by greenlucky on 4/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITStorageServiceTest {

    @Autowired
    private StorageService storageService;


    @Test
    public void detectImage() throws Exception {
        String filePath = "/Users/greenlucky/Downloads/742f7c56795556117de7cd4e1492cf2d.jpg";
        GoogleResponses googleResponses = storageService.detectImage(filePath);
        System.out.println(googleResponses);
    }

}