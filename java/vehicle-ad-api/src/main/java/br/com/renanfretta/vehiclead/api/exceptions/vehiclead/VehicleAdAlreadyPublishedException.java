package br.com.renanfretta.vehiclead.api.exceptions.vehiclead;

import br.com.renanfretta.vehiclead.api.commons.BusinessException;
import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;

public class VehicleAdAlreadyPublishedException extends BusinessException {

    public VehicleAdAlreadyPublishedException(MessagesProperty messagesProperty, Long id) {
        super(messagesProperty.getMessage(MessagesPropertyEnum.ERROR_VEHICLE_AD_ALREADY_PUBLISHED, new Object[]{id}));
    }

}
