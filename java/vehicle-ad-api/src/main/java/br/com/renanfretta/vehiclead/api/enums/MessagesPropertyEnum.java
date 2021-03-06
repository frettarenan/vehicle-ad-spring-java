package br.com.renanfretta.vehiclead.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessagesPropertyEnum {

    ERROR_DEFAULT("error.default"),
    ERROR_RESOURCE_NOT_FOUND("error.resource-not-found"),
    ERROR_RESOURCE_NOT_FOUND_DETAILED("error.resource-not-found-detailed"),
    ERROR_VEHICLE_AD_ALREADY_PUBLISHED("error.vehicle-ad-already-published"),
    ERROR_VEHICLE_DEALER_TIER_LIMIT_EXCEEDED_EXCEPTION("error.vehicle-dealer-tier-limit-exceeded");

    private final String key;

}
