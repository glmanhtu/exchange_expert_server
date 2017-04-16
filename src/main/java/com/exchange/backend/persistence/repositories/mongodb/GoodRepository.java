package com.exchange.backend.persistence.repositories.mongodb;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */
@Repository
public interface GoodRepository extends MongoRepository<Good, String> {

    List<Good> findByIdIn(List<String> ids);

    Page<Good> findByIdIn(List<String> ids, Pageable pageable);

    Good findBySlug(String slug);

    Good findByCategorySlugAndSlug(String categorySlug, String slug);

    Page<Good> findByPostById(String ofUser, Pageable pageable);
}
