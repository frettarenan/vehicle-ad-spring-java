package br.com.renanfretta.vehiclead.api.exceptions.vehicledealer;

import br.com.renanfretta.vehiclead.api.commons.BusinessException;
import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;

public class VehicleDealerTierLimitExceededException extends BusinessException {

    public VehicleDealerTierLimitExceededException(MessagesProperty messagesProperty) {
        super(messagesProperty.getMessage(MessagesPropertyEnum.ERROR_VEHICLE_DEALER_TIER_LIMIT_EXCEEDED_EXCEPTION));
    }

}
