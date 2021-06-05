package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import br.com.renanfretta.vehiclead.api.exceptions.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.repositories.VehicleDealerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Validated
public class VehicleDealerService {

    private final VehicleDealerRepository repository;
    private final OrikaMapper orikaMapper;
    private final MessagesProperty messagesProperty;

    public List<VehicleDealerOutputDTO> findAll() {
        List<VehicleDealer> list = repository.findAll();
        log.info("VehicleDealerRepository/findAll was successful");
        return orikaMapper.mapAsList(list, VehicleDealerOutputDTO.class);
    }

    public VehicleDealerOutputDTO findById(Long id) throws ResourceNotFoundException {
        try {
            VehicleDealer entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty.getErrorMessageResourceNotFoundFindById(VehicleDealer.class, id)));
            log.info("VehicleDealerRepository/findById(" + id + ") was successful");
            return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
        } catch (ResourceNotFoundException e) {
            log.warn("VehicleDealerRepository/findById(" + id + ") notFound");
            throw e;
        }
    }

}
