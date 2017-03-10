package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.Rating;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.RatingService;
import com.exchange.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by greenlucky on 3/10/17.
 */
@RestController
@RequestMapping(RatingHandler.API_RATING_URL)
public class RatingHandler {

    public static final String API_RATING_URL = "/rating";

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    /**
     * Adds rating for user given by forEmailUser, byEmailUser and star.
     *
     * @param forEmailUser
     * @param byEmailUser
     * @param star
     * @return A rating if success or message forEmailUser or byEmailUser not found
     * @exception MessageEnum
     */
    @GetMapping("/add")
    public ResponseEntity<Object> rating(@RequestParam("forEmailUser") String forEmailUser,
                                         @RequestParam("byEmailUser") String byEmailUser,
                                         @RequestParam("star") float star){

        User forUser = userService.getOne(forEmailUser);
        User byUser = userService.getOne(byEmailUser);

        Message message = null;

        if(forUser == null){
            message = new Message(MessageEnum.USER_NOT_FOUND, forEmailUser);
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }

        if(byUser == null){
            message = new Message(MessageEnum.USER_NOT_FOUND, byEmailUser);
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }

        Rating rating = ratingService.add(forUser, byEmailUser, star);

        return new ResponseEntity<Object>(rating, HttpStatus.OK);
    }
}
