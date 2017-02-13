package com.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Mrs Hoang on 20/12/2016.
 */
@Configuration
public class I18NConfig {

    private static final long TIME_CACH_MESSAGE_SOURCE = 1800;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource
                = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:i18n/messages");
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");


        //Check for new every 30 minutes
        reloadableResourceBundleMessageSource.setCacheMillis(TIME_CACH_MESSAGE_SOURCE);
        return reloadableResourceBundleMessageSource;
    }
}
