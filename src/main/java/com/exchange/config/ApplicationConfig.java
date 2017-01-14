package com.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableJpaRepositories(basePackages =  "com.exchange.backend.persistence.repositories")
@EnableTransactionManagement
@PropertySource("config/application-common.properties")
public class ApplicationConfig {
}
