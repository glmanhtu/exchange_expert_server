package com.exchange.backend.persistence.repositories.mongodb;

import com.exchange.backend.persistence.domain.MailPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by greenlucky on 4/16/17.
 */
@Repository
public interface MailPostRepository extends MongoRepository<MailPost, String> {


    Page<MailPost> findByForUser(String forUser, Pageable pageable);

    Page<MailPost> findByForUserOrderByReadAscCreatedDateDesc(String userId, Pageable pageable);
}
