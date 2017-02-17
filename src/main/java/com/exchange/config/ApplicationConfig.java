package com.exchange.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories("com.exchange.backend.persistence.repositories")
@PropertySource("classpath:config/application-common.properties")
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public String toString() {
        return "{ApplicationConfig}";
    }

}
