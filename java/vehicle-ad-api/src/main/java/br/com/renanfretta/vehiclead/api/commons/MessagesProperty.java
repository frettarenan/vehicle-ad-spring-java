package br.com.renanfretta.vehiclead.api.commons;

import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessagesProperty {

    private final MessageSource messageSource;

    public MessagesProperty(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getErrorMessageResourceNotFoundFindById(Class _class, Long id) {
        return messageSource.getMessage(MessagesPropertyEnum.ERROR_RESOURCE_NOT_FOUND_FIND_BY_ID.getKey(), new Object[]{_class.getName(), id}, LocaleContextHolder.getLocale());
    }

}