package com.exchange.backend.persistence.domain;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by optimize on 2/7/17.
 */
@Document(indexName = "goods", type = "good", shards = 1)
public class ElasticGood extends GoodAbstract {

    public ElasticGood() {

    }

}
