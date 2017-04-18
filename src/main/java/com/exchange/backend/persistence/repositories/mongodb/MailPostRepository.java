package com.exchange.backend.persistence.repositories.mongodb;

import com.exchange.backend.persistence.domain.MailPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 4/16/17.
 */
@Repository
public interface MailPostRepository extends MongoRepository<MailPost, String> {


    Page<MailPost> findByForUser(String forUser, Pageable pageable);

    List<MailPost> findByForUserAndRead(String forUser, boolean read);

    Page<MailPost> findByForUserOrderByReadAscCreatedDateDesc(String userId, Pageable pageable);
}
