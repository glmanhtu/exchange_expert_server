package com.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@Profile("prod")
@PropertySource("config/application-prod.properties")
public class ProductionConfig {
}
