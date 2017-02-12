package com.exchange.restapi;

import com.exchange.backend.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by optimize on 2/12/17.
 */
@RestController
@EnableResourceServer
@RequestMapping("/good")
public class GoodHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);

    @Autowired
    private GoodService goodService;
}
