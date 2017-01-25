package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.Content;
import com.exchange.backend.persistence.domain.Rating;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/25/17.
 */
@Service
public class RatingService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Ratings user given by rated email user, rating email user and start.
     *
     * @param forEmailUser
     * @param byEmailUser
     * @param star
     * @return A Rating of user after rating
     */
    public Rating add(String forEmailUser, String byEmailUser, float star){
        User forUser = userRepository.findOne(forEmailUser);

        //repairs content
        Content content = new Content();
        content.setBy(byEmailUser);
        content.setOn(LocalDateTime.now(Clock.systemDefaultZone()));
        content.setValue(star);

        Rating rating = new Rating();
        List<Content> contents = new ArrayList<>();

        //checking rating have been existed in user
        // if exist rating then assign rating and contents
        if(forUser.getRating()!= null) {
            rating = forUser.getRating();
            contents = rating.getContent();
        }

        // checking whether user has been existed (rated) in list content of rating or not.
        // If user exist which will be update value of rating
        // otherwise add to contents of rating
        if(contents.contains(content)) {
            int index = contents.indexOf(content);
            contents.set(index, content);
        }else
            contents.add(content);

        rating.setAvg(average(contents));
        rating.setContent(contents);

        forUser.setRating(rating);
        userRepository.save(forUser);

        return rating;
    }

    /**
     * Averages value of Rating's list content.
     *
     * @param contents
     * @return average of list content
     */
    private double average(List<Content> contents) {
        double sum = 0.0;
        for (Content content : contents)
            sum+=content.getValue();
        return sum/contents.size();
    }
}
