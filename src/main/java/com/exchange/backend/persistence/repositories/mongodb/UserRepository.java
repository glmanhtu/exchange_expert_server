package com.exchange.backend.persistence.repositories.mongodb;

import com.exchange.backend.persistence.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{feedbacks: {$slice: [0, 1]}}")
    List<User> findAll();

    @Query(value = "{}", fields = "{feedbacks: {$slice: [0, 1]}}")
    Page<User> findAll(Pageable pageable);

    @Query(value = "{_id: {$in: ?0}}", fields = "{feedbacks: {$slice: [1, 2]}}")
    Page<User> findByIdIn(List<String> ids, Pageable pageable);

    @Query(value = "{_id: ?0}", fields = "{feedbacks: {$slice: [?1, ?2]}}")
    User findById(String id, int skip, int limit);
}
