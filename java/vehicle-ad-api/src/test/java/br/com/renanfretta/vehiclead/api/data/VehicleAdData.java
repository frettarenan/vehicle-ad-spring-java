package br.com.renanfretta.vehiclead.api.data;

import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdInsertInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.entities.VehicleAdState;
import br.com.renanfretta.vehiclead.api.enums.VehicleAdStateEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleAdData {

    public static VehicleAd getVehicleAd01() {
        return VehicleAd.builder()
                .id(1L)
                .brand("Honda")
                .model("Civic")
                .modelYear((short) 2021)
                .manufacturingYear((short) 2021)
                .color("white")
                .mileage(0)
                .used(false)
                .vehicleDealer(VehicleDealerData.getVehicleDealer01())
                .state(VehicleAdState.builder().id(VehicleAdStateEnum.DRAFT.getId()).name(VehicleAdStateEnum.DRAFT.name()).build())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<VehicleAd> getListVehicleAd01() {
        List<VehicleAd> list = new ArrayList<>();
        list.add(getVehicleAd01());
        return list;
    }

    public static VehicleAdInsertInputDTO getVehicleAdInsertInputDTO01() {
        return VehicleAdInsertInputDTO.builder()
                .brand("Honda")
                .model("Civic")
                .modelYear((short) 2021)
                .manufacturingYear((short) 2021)
                .color("white")
                .mileage(0)
                .used(false)
                .vehicleDealer(VehicleDealerData.getVehicleDealerOutputDTO01())
                .build();
    }

    public static Optional<VehicleAd> getOptionalVehicleAd01() {
        return Optional.of(getVehicleAd01());
    }

    public static VehicleAdUpdateInputDTO getVehicleAdUpdateInputDTO02() {
        return VehicleAdUpdateInputDTO.builder()
                .brand("Jeep")
                .model("Compass")
                .modelYear((short) 2018)
                .manufacturingYear((short) 2019)
                .color("black")
                .mileage(25645)
                .used(true)
                .build();
    }

    public static Optional<VehicleAd> getOptionalVehicleAd02() {
        return Optional.of(getVehicleAd02());
    }

    public static VehicleAd getVehicleAd02() {
        return VehicleAd.builder()
                .id(1L)
                .brand("Jeep")
                .model("Compass")
                .modelYear((short) 2018)
                .manufacturingYear((short) 2019)
                .color("black")
                .mileage(25645)
                .used(true)
                .vehicleDealer(VehicleDealerData.getVehicleDealer01())
                .state(VehicleAdState.builder().id(VehicleAdStateEnum.PUBLISHED.getId()).name(VehicleAdStateEnum.DRAFT.name()).build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .build();
    }

}
