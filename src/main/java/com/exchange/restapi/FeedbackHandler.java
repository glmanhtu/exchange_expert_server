package com.exchange.restapi;

import com.exchange.backend.persistence.domain.Feedback;
import com.exchange.backend.service.UserService;
import com.exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.exchange.restapi.FeedbackHandler.REST_API_FEEDBACK;

/**
 * Created by optimize on 3/19/17.
 */
@RestController
@RequestMapping(REST_API_FEEDBACK)
public class FeedbackHandler {
    public static final String REST_API_FEEDBACK = "/feedback";

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getListFeedback(Principal principal, @RequestParam int skip, @RequestParam int limit) {
        List<Feedback> feedbacks = userService.getFeedbacks(principal.getName(), skip, limit);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createFeedback(Principal principal,
                                            @RequestBody Feedback feedback, @RequestParam String user) {
        feedback.setBy(principal.getName());
        feedback.setCommentDate(Utils.getCurrentTimestamp());
        userService.saveFeedback(user, feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
