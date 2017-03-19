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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
     * Adds rating for user given by forEmailUser and star.
     *
     * @param forEmailUser
     * @param star
     * @return A rating if success or message forEmailUser or not found
     * @exception MessageEnum
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> rating(Principal principal, @RequestParam("forEmailUser") String forEmailUser,
                                         @RequestParam("star") float star) {

        User forUser = userService.getOne(forEmailUser);

        if (forUser == null) {
            Message message = new Message(MessageEnum.USER_NOT_FOUND, forEmailUser);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        Rating rating = ratingService.add(forUser, principal.getName(), star);

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
