package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdInsertInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.entities.VehicleAdState;
import br.com.renanfretta.vehiclead.api.enums.VehicleAdStateEnum;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.exceptions.vehiclead.VehicleAdAlreadyPublishedException;
import br.com.renanfretta.vehiclead.api.exceptions.vehicledealer.VehicleDealerTierLimitExceededException;
import br.com.renanfretta.vehiclead.api.repositories.vehiclead.VehicleAdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Validated
public class VehicleAdService {

    private final VehicleAdRepository repository;
    private final OrikaMapper orikaMapper;
    private final ObjectMapperSpecialized objectMapper;
    private final MessagesProperty messagesProperty;

    public List<VehicleAdOutputDTO> findByVehicleDealerAndState(@NotNull @Range(min = 1) Long vehicleDealerId, Short vehicleAdStateId) {
        List<VehicleAd> list = repository.findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId);
        log.info("VehicleAdRepository/findByVehicleDealerAndState(" + vehicleDealerId + ", " + vehicleAdStateId + ") was successful");
        return orikaMapper.mapAsList(list, VehicleAdOutputDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleAdOutputDTO save(@Valid VehicleAdInsertInputDTO inputDTO) {
        VehicleAd entity = orikaMapper.map(inputDTO, VehicleAd.class);

        entity.setState(VehicleAdState.builder().id(VehicleAdStateEnum.DRAFT.getId()).build());

        entity.setCreatedAt(LocalDateTime.now());
        entity = repository.save(entity);

        entity = repository.findById(entity.getId()).get();
        log.info("VehicleAdRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleAdOutputDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleAdOutputDTO updateAll(@NotNull @Range(min = 1) Long id, @Valid VehicleAdUpdateInputDTO inputDTO) throws ResourceNotFoundException {
        VehicleAd entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleAd.class, id));

        entity.setBrand(inputDTO.getBrand());
        entity.setModel(inputDTO.getModel());
        entity.setModelYear(inputDTO.getModelYear());
        entity.setManufacturingYear(inputDTO.getManufacturingYear());
        entity.setColor(inputDTO.getColor());
        entity.setMileage(inputDTO.getMileage());
        entity.setUsed(inputDTO.getUsed());

        entity.setUpdatedAt(LocalDateTime.now());
        repository.save(entity);

        entity = repository.findById(entity.getId()).get();
        log.info("VehicleAdRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleAdOutputDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleAdOutputDTO publish(@NotNull @Range(min = 1) Long id, @NotNull Boolean respectLimit) throws ResourceNotFoundException, VehicleAdAlreadyPublishedException, VehicleDealerTierLimitExceededException {
        VehicleAd entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleAd.class, id));

        validateVehicleAdAlreadyPublished(entity);
        businessRuleVehicleDealerTierLimit(respectLimit, entity);

        entity.setState(VehicleAdState.builder().id(VehicleAdStateEnum.PUBLISHED.getId()).build());
        entity.setPublishedAt(LocalDateTime.now());

        repository.save(entity);
        entity = repository.findById(entity.getId()).get();
        log.info("VehicleAdRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleAdOutputDTO.class);
    }

    private void businessRuleVehicleDealerTierLimit(Boolean respectLimit, VehicleAd entity) throws VehicleDealerTierLimitExceededException, ResourceNotFoundException {
        List<VehicleAd> publishedList = repository.findByVehicleDealerAndState(entity.getVehicleDealer().getId(), VehicleAdStateEnum.PUBLISHED.getId());

        if (publishedList.size() < entity.getVehicleDealer().getTierLimit().intValue())
            return;

        if (respectLimit)
            throw new VehicleDealerTierLimitExceededException(messagesProperty);

        unpublish(publishedList.get(0).getId());
    }

    private void validateVehicleAdAlreadyPublished(VehicleAd entity) throws VehicleAdAlreadyPublishedException {
        if (entity.getState().getId().equals(VehicleAdStateEnum.PUBLISHED.getId()))
            throw new VehicleAdAlreadyPublishedException(messagesProperty, entity.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleAdOutputDTO unpublish(@NotNull @Range(min = 1) Long id) throws ResourceNotFoundException {
        VehicleAd entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messagesProperty, VehicleAd.class, id));

        entity.setState(VehicleAdState.builder().id(VehicleAdStateEnum.DRAFT.getId()).build());
        entity.setPublishedAt(null);

        repository.save(entity);
        entity = repository.findById(entity.getId()).get();
        log.info("VehicleAdRepository/save(" + objectMapper.writeValueAsStringNoException(entity) + ") was successful");
        return orikaMapper.map(entity, VehicleAdOutputDTO.class);
    }

}
