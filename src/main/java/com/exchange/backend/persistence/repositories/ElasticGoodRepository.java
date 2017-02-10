package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.utils.search.Specification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by optimize on 2/8/17.
 */
public interface ElasticGoodRepository extends ElasticsearchRepository<ElasticGood, String> {
    List<ElasticGood> findAll(Specification<Good> spec);
}
