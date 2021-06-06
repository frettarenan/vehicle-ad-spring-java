package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.repositories.vehicledealer.VehicleDealerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Service
@Validated
public class VehicleDealerService {

    private final VehicleDealerRepository repository;
    private final OrikaMapper orikaMapper;
    private final ObjectMapperSpecialized objectMapper;
    private final MessagesProperty messagesProperty;

    public List<VehicleDealerOutputDTO> findAll() {
        List<VehicleDealer> list = repository.findAll();
        log.info("VehicleDealerRepository/findAll was successful");
        return orikaMapper.mapAsList(list, VehicleDealerOutputDTO.class);
    }

    public VehicleDealerOutputDTO findById(@NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        try {
            VehicleDealer entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleDealer.class, id));
            log.info("VehicleDealerRepository/findById(" + id + ") was successful");
            return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
        } catch (ResourceNotFoundException e) {
            log.warn("VehicleDealerRepository/findById(" + id + ") notFound");
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDealerOutputDTO save(@Valid VehicleDealerInputDTO inputDTO) {
        VehicleDealer entity = orikaMapper.map(inputDTO, VehicleDealer.class);

        repository.save(entity);
        entity = repository.findById(entity.getId()).get();
        log.info("VehicleDealerRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
    }

    private void validateVehicleDealerExists(@NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleDealer.class, id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDealerOutputDTO updateAll(@NotNull @Range(min = 1) Long id, @Valid VehicleDealerInputDTO inputDTO) throws ResourceNotFoundException {
        validateVehicleDealerExists(id);

        VehicleDealer entity = orikaMapper.map(inputDTO, VehicleDealer.class);
        entity.setId(id);

        repository.save(entity);
        entity = repository.findById(entity.getId()).get();
        log.info("VehicleDealerRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDealerOutputDTO partialUpdate(@NotNull @Range(min = 1) Long id, VehicleDealerInputDTO inputDTO) throws ResourceNotFoundException {
        VehicleDealer entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleDealer.class, id));

        if (nonNull(inputDTO.getName()))
            entity.setName(inputDTO.getName());

        if (nonNull(inputDTO.getPhone()))
            entity.setPhone(inputDTO.getPhone());

        if (nonNull(inputDTO.getEmail()))
            entity.setEmail(inputDTO.getEmail());

        if (nonNull(inputDTO.getAddress()))
            entity.setAddress(inputDTO.getAddress());

        if (nonNull(inputDTO.getTierLimit()))
            entity.setTierLimit(inputDTO.getTierLimit());

        repository.save(entity);
        entity = repository.findById(entity.getId()).get();
        log.info("VehicleDealerRepository/partialUpdate(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDealerOutputDTO deleteById(@NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        VehicleDealer entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleDealer.class, id));
        repository.delete(entity);
        return orikaMapper.map(entity, VehicleDealerOutputDTO.class);
    }

}
