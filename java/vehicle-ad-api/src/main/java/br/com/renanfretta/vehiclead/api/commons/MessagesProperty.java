package br.com.renanfretta.vehiclead.api.commons;

import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessagesProperty {

    private final MessageSource messageSource;

    public String getMessage(MessagesPropertyEnum messagesPropertyEnum) {
        return messageSource.getMessage(messagesPropertyEnum.getKey(), null, LocaleContextHolder.getLocale());
    }

    public String getMessage(MessagesPropertyEnum messagesPropertyEnum, Object[] obj) {
        return messageSource.getMessage(messagesPropertyEnum.getKey(), obj, LocaleContextHolder.getLocale());
    }

    public String getMessage(MessageSourceResolvable resolvable) {
        return messageSource.getMessage(resolvable, LocaleContextHolder.getLocale());
    }

}