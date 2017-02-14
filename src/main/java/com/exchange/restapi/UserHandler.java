package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
public class UserHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserHandler.class);

    /**
     * URL THIS REST API OF USER
     */
    public static final String REST_API_USER_INFO = "/user/info";

    @Autowired
    private UserService userService;

    @RequestMapping(value = REST_API_USER_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@RequestParam("email") String email, Locale locale) {

        User user = userService.getOne(email);

        //if user is null return message not found
        if (user == null) {
            Message message = new Message(MessageEnum.USER_NOT_FOUND);
            return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
}