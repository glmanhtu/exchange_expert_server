package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.Comment;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by greenlucky on 1/31/17.
 */
@Service
public class CommentService {

    @Autowired
    private GoodService goodService;

    /**
     * Adds comment to good given by goodId, byUser and message
     * @param goodId
     * @param byUser
     * @param message
     * @see Good
     * @see Comment
     */
    public void addComment(String goodId, String byUser, String message) {

        //create new comment given by goodId, byUser and message
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setBy(byUser);
        comment.setCommentDate(Utils.getCurrentTimestamp());

        Good good = goodService.getOne(goodId);
        good.getComments().add(comment);

        //update good
        goodService.update(good);
    }

    /**
     * Updates comment given by goodId, index of comment and new message
     *
     * @param goodId
     * @param index
     * @param newMessage
     * @see Good
     * @see Comment
     */
    public void updateComment(String goodId, int index, String newMessage) {

        Good good = goodService.getOne(goodId);
        good.getComments().get(index).setMessage(newMessage);

        //update good
        goodService.update(good);
    }

    /**
     * Deletes comment given by goodId and index of comment
     * @param goodId
     * @param index
     * @see Good
     */
    public void deleteComment(String goodId, int index) {
        Good good = goodService.getOne(goodId);
        good.getComments().remove(index);

        //update good
        goodService.update(good);
    }
}
