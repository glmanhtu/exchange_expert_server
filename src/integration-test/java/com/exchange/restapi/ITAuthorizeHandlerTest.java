package com.exchange.restapi;

import com.exchange.backend.service.GoogleService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

/**
 * Created by greenlucky on 4/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITAuthorizeHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GoogleService googleService;


    @Test
    public void loginFacebook() throws Exception {

        String accessToken = "EAAH1KKCT2IYBAJpYlwBVFYtt6qtZAP0R3d9JrToWDiEeiMT1VQR6m3Rpegxp2e7eCVBxRQt3bXyuKZBf8FzDflXFKLEMd16SgswVpSa4Gf5MJzd7upPI3LNKZB43PDD1G6BbOykxnUu989uT3hU9JClshJwf7urBNLkZBhigJDeDGJUqIWZBt0oGpcMplZBt0ZD";

        Object obj = restTemplate.getForEntity("/login/facebook?accessToken={accessToken}", Object.class, accessToken);

        System.out.println(obj);

    }

    @Test
    public void loginFacebookA() throws Exception {

        String accessToken = "EAAH1KKCT2IYBAFt3M0OX4ZC7GIosQUPi3MhCj7Yc1Mfmg4y7x7kFdtjz0PDLKcCqHGhH0qtpRGWOfKCP97A5ZCO1oUjIoZAWelL3NK6yrb8Vy4e3phr7xTNUcQqGv66YXyWtb8HcXevhi9Ya1HqZCZAtlaxN7tRjqBLopOLcRKINiHWdciuVZByQbspO0W7x4ZD";

        org.springframework.social.facebook.api.User userFacebook = null;
        //try {
        Facebook facebook = new FacebookTemplate(accessToken);
        userFacebook = facebook.userOperations().getUserProfile();

        URI uri = URIBuilder.fromUri("http://graph.facebook.com/" + userFacebook.getId() + "/picture?type=large&redirect=false").build();
        System.out.println(uri);
        JsonNode response = restTemplate.getForObject(uri, JsonNode.class);
        System.out.println("User avatar: " + response.get("data").get("url").textValue());

    }

    @Test
    public void loginGoogle() throws Exception {

        String accessToken = "ya29.GmEnBO2DJ23tj61b0gVuiwi2tU0mlIvQPKL2aAGxXtHfV6GWzerVRhz1dViXMU8liatCZN_7kTPbR1WXAb1iaJA4FD6cfrsT5SH_NyaqmyhL03bA374EuH-mqJ8z9Xd1OTrb";

        Object obj = restTemplate.getForEntity("/login/google?accessToken={accessToken}", Object.class, accessToken);

        System.out.println(obj);
    }
}