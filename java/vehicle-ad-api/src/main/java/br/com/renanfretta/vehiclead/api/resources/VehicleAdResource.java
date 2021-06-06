package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdInsertInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.exceptions.vehiclead.VehicleAdAlreadyPublishedException;
import br.com.renanfretta.vehiclead.api.services.VehicleAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/vehicle-ad")
public class VehicleAdResource {

    private final VehicleAdService service;
    private final ObjectMapperSpecialized objectMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<VehicleAdOutputDTO>> findByVehicleDealerAndState(@RequestParam(name = "vehicleDealerId") @Size(min = 1) Long vehicleDealerId,
                                                                                @RequestParam(name = "vehicleAdStateId", required = false) Short vehicleAdStateId) {
        log.info("VehicleAdResource/findByVehicleDealerAndState(" + vehicleDealerId + ", " + vehicleAdStateId + ") was called");
        List<VehicleAdOutputDTO> list = service.findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId);
        if (list == null || list.isEmpty()) {
            log.info("VehicleAdResource/findByVehicleDealerAndState(" + vehicleDealerId + ", " + vehicleAdStateId + " noContent");
            return ResponseEntity.noContent().build();
        }
        log.info("VehicleAdResource/findByVehicleDealerAndState(" + vehicleDealerId + ", " + vehicleAdStateId + " ok");
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<VehicleAdOutputDTO> save(@Valid @RequestBody VehicleAdInsertInputDTO inputDTO) {
        log.info("VehicleAdResource/save( " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleAdOutputDTO outputDTO = service.save(inputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(outputDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleAdOutputDTO> updateAll(@PathVariable Long id, @Valid @RequestBody VehicleAdUpdateInputDTO inputDTO) throws ResourceNotFoundException {
        log.info("VehicleAdResource/updateAll(id: " + id + " obj: " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleAdOutputDTO outputDTO = service.updateAll(id, inputDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @PatchMapping(value = "/{id}/publish")
    public ResponseEntity<VehicleAdOutputDTO> publish(@PathVariable Long id, @RequestParam(name = "respectLimit", required = false) String respectLimitStr) throws ResourceNotFoundException, VehicleAdAlreadyPublishedException {
        boolean respectLimit = isNull(respectLimitStr) || !respectLimitStr.equalsIgnoreCase("false");
        log.info("VehicleAdResource/publish(id: " + id + " respectLimit: " + respectLimit + ") was called");
        VehicleAdOutputDTO outputDTO = service.publish(id, respectLimit);
        return ResponseEntity.ok(outputDTO);
    }

    @PatchMapping(value = "/{id}/unpublish")
    public ResponseEntity<VehicleAdOutputDTO> unpublish(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("VehicleAdResource/unpublish(" + id + ") was called");
        VehicleAdOutputDTO outputDTO = service.unpublish(id);
        return ResponseEntity.ok(outputDTO);
    }

}
