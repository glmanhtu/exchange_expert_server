package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.dto.UserDto;
import com.exchange.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
@RequestMapping(UserHandler.REST_API_USER)
public class UserHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserHandler.class);

    /**
     * URL THIS REST API OF USER
     */
    public static final String REST_API_USER = "/user";

    @Autowired
    private UserService userService;

    /**
     * Creates new user given by user.
     *
     * @param user
     * @return A user DTO or exception if email has been existed
     * @see MessageEnum
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.getOne(user.getId()) != null) {
            Message message = new Message(MessageEnum.USER_EMAIL_INVALID);
            return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
        }

        user = userService.create(user);

        //convert to user dto
        UserDto userDto = new UserDto(user);

        return new ResponseEntity<Object>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam("email") String email) {

        User user = userService.getOne(email);

        //if user is null return message not found
        if (user == null) {
            Message message = new Message(MessageEnum.USER_NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        UserDto userDto = new UserDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
