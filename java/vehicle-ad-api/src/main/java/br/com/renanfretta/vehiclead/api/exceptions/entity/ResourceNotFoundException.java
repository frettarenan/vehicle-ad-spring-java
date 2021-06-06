package br.com.renanfretta.vehiclead.api.exceptions.entity;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(MessagesProperty messagesProperty, Class _class, Long id) {
        super(messagesProperty.getMessage(MessagesPropertyEnum.ERROR_RESOURCE_NOT_FOUND_FIND_BY_ID, new Object[]{_class.getName(), id}));
    }

}
