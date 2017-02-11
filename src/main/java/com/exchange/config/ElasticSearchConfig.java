package com.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by optimize on 2/8/17.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.exchange.backend.persistence.repositories.elasticsearch")
public class ElasticSearchConfig {

}
