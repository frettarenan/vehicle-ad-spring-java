package br.com.renanfretta.vehiclead.api.data;

import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDealerData {

    public static VehicleDealerInputDTO getVehicleDealerInputDTO01() {
        return VehicleDealerInputDTO.builder()
                .name("Test Name")
                .phone("1234567890")
                .email("test@test.com")
                .address("Test Street")
                .tierLimit(1)
                .build();
    }

    public static VehicleDealer getVehicleDealer01() {
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

    public static VehicleDealerOutputDTO getVehicleDealerOutputDTO01() {
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

    public static VehicleDealerInputDTO getVehicleDealerInputDTO02() {
        return VehicleDealerInputDTO.builder()
                .name("Test Name 2")
                .phone("0123456789")
                .email("test2@test.com")
                .address("Test 2 Street")
                .tierLimit(2)
                .build();
    }

    public static VehicleDealer getVehicleDealer02() {
        return VehicleDealer.builder()
                .id(1L)
                .name("Test Name 2")
                .phone("0123456789")
                .email("test2@test.com")
                .address("Test 2 Street")
                .tierLimit(2)
                .build();
    }

    public static Optional<VehicleDealer> getOptionalVehicleDealer02() {
        return Optional.of(getVehicleDealer02());
    }

}
