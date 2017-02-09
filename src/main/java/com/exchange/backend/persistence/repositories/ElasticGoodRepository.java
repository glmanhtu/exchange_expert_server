package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.ElasticGood;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by optimize on 2/8/17.
 */
public interface ElasticGoodRepository extends ElasticsearchRepository<ElasticGood, String> {
    List<ElasticGood> findAll(Predicate predicate);
}
