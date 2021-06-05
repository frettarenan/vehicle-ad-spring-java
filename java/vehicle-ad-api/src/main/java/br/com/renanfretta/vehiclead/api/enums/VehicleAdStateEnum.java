package br.com.renanfretta.vehiclead.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VehicleAdStateEnum {

    DRAFT(1),
    PUBLISHED(2);

    private final int id;

}
