package com.eon.springbootdatamanagement.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageBundle {
    private static MessageSource messageSource;

    @Autowired
    public MessageBundle(MessageSource messageSource) {
        MessageBundle.messageSource = messageSource;
    }

    public static String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String getMessageByCode(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
