package com.exchange.restapi;

import com.exchange.backend.service.GoogleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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

        String accessToken = "EAAH1KKCT2IYBANsVt4ilVgSvYss8RxNm7K6WCnUvCXs93lZBKKTZB8MJoDcWawqOdrN7sWGWjMLIJUBmud4GJZCSyhUxNUJItfZCTaBDUIyMJrt5me6xmGMZAVSbpPfyXHE5oCN9cvmqNmqhBIcncLFvgNA6K03a0o04yZBN6hwYuJaEsuROoZBW3EAuuJxIAIZD";

        Object obj = restTemplate.getForEntity("/login/facebook?accessToken={accessToken}", Object.class, accessToken);

        System.out.println(obj);

    }

    @Test
    public void loginFacebookA() throws Exception {

        String accessToken = "EAAH1KKCT2IYBANsVt4ilVgSvYss8RxNm7K6WCnUvCXs93lZBKKTZB8MJoDcWawqOdrN7sWGWjMLIJUBmud4GJZCSyhUxNUJItfZCTaBDUIyMJrt5me6xmGMZAVSbpPfyXHE5oCN9cvmqNmqhBIcncLFvgNA6K03a0o04yZBN6hwYuJaEsuROoZBW3EAuuJxIAIZD";

        org.springframework.social.facebook.api.User userFacebook = null;
        //try {
        Facebook facebook = new FacebookTemplate(accessToken);
        userFacebook = facebook.userOperations().getUserProfile();
        System.out.println(userFacebook.getEmail());

    }

    @Test
    public void loginGoogle() throws Exception {

        String accessToken = "a29.GlshBN5L7gLqKc_P9frjQPrdExl3nKdpZSiQff-9ZFh6vf9IcPAEDcAMD0jYvCcjKBfG-7cPK-4zjfkjZcE2cor47bIE77MhssQR51gmC3qmJqWRLb1gPGaQxqu5";

        Object obj = restTemplate.getForEntity("/login/google?accessToken={accessToken}", Object.class, accessToken);

        System.out.println(obj);
    }
}