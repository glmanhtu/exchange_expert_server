package com.exchange.backend.persistence.domain;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by Mrs Hoang on 09/02/2017.
 */
@Document(indexName = "users", type = "user", shards = 1)
public class ElasticUser extends UserAbstract {

    public ElasticUser() {

    }
}
