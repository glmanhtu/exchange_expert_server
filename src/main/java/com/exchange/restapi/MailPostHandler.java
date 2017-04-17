package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.MailPost;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.service.MailPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;


/**
 * Created by greenlucky on 4/16/17.
 */
@RestController
@RequestMapping(MailPostHandler.API_MAIL_POST)
public class MailPostHandler {

    /**
     * The application logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailPostHandler.class);

    public static final String API_MAIL_POST = "/mail-post";

    @Autowired
    private MailPostService mailPostService;

    @GetMapping("/detail/{mailPostId}")
    public ResponseEntity<Object> getMailPost(@PathVariable String mailPostId) {
        MailPost mailPost = mailPostService.getOne(mailPostId);
        if (mailPost == null) {
            Message message = new Message(MessageEnum.MAIL_POST_NOT_FOUND, mailPostId);
            return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(mailPost, HttpStatus.OK);
    }

    @PutMapping("/{mailPostId}")
    public ResponseEntity<Object> makeAsRead(@PathVariable String mailPostId) {
        MailPost mailPost = mailPostService.getOne(mailPostId);
        if (mailPost == null) {
            Message message = new Message(MessageEnum.MAIL_POST_NOT_FOUND, mailPostId);
            return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
        }
        mailPost.setRead(true);
        mailPostService.makeAsRead(mailPost);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<Object> getMailPostOfUser(Principal principal, Pageable pageable) {

        String userId = principal.getName();
        Page<MailPost> mailPosts = mailPostService.getMailPostofUser(userId, pageable);
        return new ResponseEntity<Object>(mailPosts, HttpStatus.OK);
    }
}
