package br.com.renanfretta.vehiclead.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VehicleAdStateEnum {

    DRAFT((short) 1),
    PUBLISHED((short) 2);

    private final short id;

}
