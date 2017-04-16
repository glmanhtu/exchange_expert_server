package com.exchange.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories("com.exchange.backend.persistence.repositories")
@PropertySource("classpath:config/application-common.properties")
@EnableMongoAuditing
public class ApplicationConfig {

    @Override
    public String toString() {
        return "{ApplicationConfig}";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
