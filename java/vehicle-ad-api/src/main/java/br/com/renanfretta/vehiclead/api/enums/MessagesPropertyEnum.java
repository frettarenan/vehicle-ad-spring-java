package br.com.renanfretta.vehiclead.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessagesPropertyEnum {

    ERROR_RESOURCE_NOT_FOUND_FIND_BY_ID("error.resource-not-found-find-by-id");

    private final String key;

}
