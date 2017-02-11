package com.exchange.backend.persistence.repositories.mongodb;

import com.exchange.backend.persistence.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

}
