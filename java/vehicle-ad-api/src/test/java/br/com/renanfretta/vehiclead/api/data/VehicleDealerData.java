package br.com.renanfretta.vehiclead.api.data;

import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDealerData {

    private static VehicleDealer getVehicleDealer01() {
        return VehicleDealer.builder()
                .id(1L)
                .name("Test Name")
                .phone("1234567890")
                .email("test@test.com")
                .address("Test Street")
                .tierLimit(1)
                .build();
    }

    public static Optional<VehicleDealer> getOptionalVehicleDealer01() {
        return Optional.of(getVehicleDealer01());
    }

    private static VehicleDealerOutputDTO getVehicleDealerOutputDTO01() {
        return VehicleDealerOutputDTO.builder()
                .id(1L)
                .name("Test Name")
                .phone("1234567890")
                .email("test@test.com")
                .address("Test Street")
                .tierLimit(1)
                .build();
    }

    public static List<VehicleDealerOutputDTO> getListVehicleDealerOutputDTO01() {
        List<VehicleDealerOutputDTO> list = new ArrayList<>();
        list.add(getVehicleDealerOutputDTO01());
        return list;
    }

}
