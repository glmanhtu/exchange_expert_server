package com.exchange.backend.persistence.repositories.elasticsearch;

import com.exchange.backend.persistence.domain.ElasticGood;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by optimize on 2/8/17.
 */
@Repository
public interface ElasticGoodRepository extends ElasticsearchRepository<ElasticGood, String> {

}
