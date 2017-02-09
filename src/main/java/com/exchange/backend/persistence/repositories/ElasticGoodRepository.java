package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by optimize on 2/8/17.
 */
public interface ElasticGoodRepository extends ElasticsearchRepository<Good, String> {
    List<Good> findAll(Predicate predicate);
}
