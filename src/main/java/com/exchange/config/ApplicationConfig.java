package com.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("config/application-common.properties")
public class ApplicationConfig {
}
