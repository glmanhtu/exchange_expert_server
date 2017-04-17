package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.Feedback;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.repositories.mongodb.UserRepository;
import com.exchange.utils.Constants;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by greenlucky on 1/24/17.
 * @since 1.0
 * @version %I%D%
 */
@Service
public class UserService implements SearchEverything<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates new user given by user.
     * Id of user is email, if will checking email before create,
     * if email exists it will be update so, before using this
     * method you should check email existing
     *
     * @param user
     * @return A user after created
     * @see User
     */
    public User create(User user) {
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        return userRepository.save(user);
    }

    /**
     * Updates user given by user. If user does not
     * exist User will be create. So before using this method
     * you should check user whether exist or not
     *
     * @param user
     * @return A user after updated
     * @see User
     */
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all user exist all database or null if not exist
     *
     * @return A list of user or null if not exist
     * @see User
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieves A Page of users give by Pageable.
     * Pageable has a field content which is List/Array of user or null if not exist.
     *
     * @param pageable
     * @return A page content list of user or null if not exist
     * @see Pageable
     * @see User
     */
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Retrieves a user given by id of user (email) or null if not exist
     *
     * @param id
     * @return A user or null if not exist
     */
    public User getOne(String id) {
        return userRepository.findById(id, 0, Constants.DEFAULT_LIMIT_FEEDBACK);
    }

    /**
     * Get list of feedbacks from user
     *
     * @param userId
     * @param skip
     * @param limit
     * @return
     */
    public List<Feedback> getFeedbacks(String userId, int skip, int limit) {
        User user = userRepository.findById(userId, skip, limit);
        return user.getFeedbacks();
    }

    /**
     * Save new feedback to user
     * @param userId
     * @param feedback
     */
    public void saveFeedback(String userId, Feedback feedback) {
        Query query = query(where("_id").is(userId));
        Update update = Update.update("feedbacks.-1", feedback);
        mongoOperations.updateFirst(query, update, User.class);
    }

    /**
     * Enables user given by userId
     * @param userId
     * @return A user after enabled
     */
    public User enableUser(String userId) {
        return changeStatus(userId, true);
    }

    /**
     * Disabled user
     * @param userId
     * @return A user after disabled
     */
    public User disableUser(String userId) {
        return changeStatus(userId, false);
    }

    /**
     * Changes status user given by userId and st
     * @param userId
     * @param st true and false
     * @return A user after changed
     */
    private User changeStatus(String userId, boolean st) {
        User user = userRepository.findOne(userId);
        user.setEnabled(st);
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(QueryBuilder queryBuilder, PageRequest pageRequest) {
        return null;
    }

}
