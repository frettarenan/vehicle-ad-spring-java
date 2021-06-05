package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import br.com.renanfretta.vehiclead.api.repositories.VehicleDealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
public class VehicleDealerService {

    private final VehicleDealerRepository repository;
    private final OrikaMapper orikaMapper;

    public List<VehicleDealerOutputDTO> findAll() {
        List<VehicleDealer> list = repository.findAll();
        return orikaMapper.mapAsList(list, VehicleDealerOutputDTO.class);
    }

}
