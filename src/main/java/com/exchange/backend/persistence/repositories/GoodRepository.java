package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by greenlucky on 1/31/17.
 */
@Repository
public interface GoodRepository extends MongoRepository<Good, Long> {

}
