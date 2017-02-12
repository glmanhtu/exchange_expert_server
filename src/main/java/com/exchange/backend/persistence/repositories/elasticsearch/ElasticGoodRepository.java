package com.exchange.backend.persistence.repositories.elasticsearch;

import com.exchange.backend.persistence.domain.ElasticGood;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by optimize on 2/8/17.
 */
public interface ElasticGoodRepository extends ElasticsearchRepository<ElasticGood, String> {
    //List<ElasticGood> findAll(Specification<Good> spec);
}
