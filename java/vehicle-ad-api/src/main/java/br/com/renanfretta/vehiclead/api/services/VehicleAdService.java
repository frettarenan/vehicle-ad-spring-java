package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.repositories.vehiclead.VehicleAdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Validated
public class VehicleAdService {

    private final VehicleAdRepository repository;
    private final OrikaMapper orikaMapper;

    public List<VehicleAdOutputDTO> findByVehicleDealerAndState(@NotNull @Size(min = 1) Long vehicleDealerId, Short vehicleAdStateId) {
        List<VehicleAd> list = repository.findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId);
        log.info("VehicleAdRepository/findByVehicleDealerAndState(" + vehicleDealerId + ", " + vehicleAdStateId + ") was successful");
        return orikaMapper.mapAsList(list, VehicleAdOutputDTO.class);
    }

}
