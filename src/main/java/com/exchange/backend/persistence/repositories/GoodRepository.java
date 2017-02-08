package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */
@Repository
public interface GoodRepository extends MongoRepository<Good, String> {
    List<Good> findByIdsIn(List<String> ids);
}
