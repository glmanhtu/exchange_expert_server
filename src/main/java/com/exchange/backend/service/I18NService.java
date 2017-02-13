package com.exchange.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Mrs Hoang on 20/12/2016.
 */
@Service
public class I18NService {

    @Autowired
    private MessageSource messageSource;

    /**
     * Get message source by messageId given by user
     * @param messageId
     * @return A message corresponding to messageId and default locale
     */
    public String getMessage(String messageId) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(messageId, locale);
    }

    /**
     * Get message source by messageId and locale
     * @param messageId
     * @param locale
     * @return A message corresponding to messageId and locale
     */
    public String getMessage(String messageId, Locale locale) {
        return messageSource.getMessage(messageId, null, locale);
    }

    public String getMessage(String messageId, String variable, Locale locale) {
        return messageSource.getMessage(messageId, new Object[]{variable}, locale);
    }
}
