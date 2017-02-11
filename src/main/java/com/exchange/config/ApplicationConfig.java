package com.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableMongoRepositories("com.exchange.backend.persistence.repositories")
@PropertySource("classpath:config/application-common.properties")
public class ApplicationConfig {

    @Override
    public String toString() {
        return "{ApplicationConfig}";
    }
}
