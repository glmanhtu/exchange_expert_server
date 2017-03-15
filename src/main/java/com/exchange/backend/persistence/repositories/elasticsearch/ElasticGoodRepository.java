package com.exchange.backend.persistence.repositories.elasticsearch;

import com.exchange.backend.persistence.domain.ElasticGood;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by optimize on 2/8/17.
 */
public interface ElasticGoodRepository extends ElasticsearchRepository<ElasticGood, String> {

    List<ElasticGood> search(QueryBuilder var1);
}
