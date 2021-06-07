package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.services.VehicleDealerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/vehicle-dealer")
public class VehicleDealerResource {

    private final VehicleDealerService service;
    private final ObjectMapperSpecialized objectMapper;

    @GetMapping
    @Operation(summary = "List all")
    public ResponseEntity<List<VehicleDealerOutputDTO>> findAll() {
        log.info("VehicleDealerResource/findAll was called");
        List<VehicleDealerOutputDTO> list = service.findAll();
        if (list == null || list.isEmpty()) {
            log.info("VehicleDealerResource/findAll noContent");
            return ResponseEntity.noContent().build();
        }
        log.info("VehicleDealerResource/findAll ok");
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find by id")
    public ResponseEntity<VehicleDealerOutputDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("VehicleDealerResource/findById(" + id + ") was called");
        VehicleDealerOutputDTO dto = service.findById(id);
        log.info("VehicleDealerResource/findById(" + id + ") ok");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Save new")
    public ResponseEntity<VehicleDealerOutputDTO> save(@RequestBody VehicleDealerInputDTO inputDTO) {
        log.info("VehicleDealerResource/save( " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleDealerOutputDTO outputDTO = service.save(inputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(outputDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update all")
    public ResponseEntity<VehicleDealerOutputDTO> updateAll(@PathVariable Long id, @RequestBody VehicleDealerInputDTO inputDTO) throws ResourceNotFoundException {
        log.info("VehicleDealerResource/updateAll(id: " + id + " obj: " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleDealerOutputDTO outputDTO = service.updateAll(id, inputDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @PatchMapping(value = "/{id}")
    @Operation(summary = "Partial update")
    public ResponseEntity<VehicleDealerOutputDTO> partialUpdate(@PathVariable Long id, @RequestBody VehicleDealerInputDTO inputDTO) throws ResourceNotFoundException {
        log.info("VehicleDealerResource/partialUpdate(id: " + id + " obj: " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleDealerOutputDTO outputDTO = service.partialUpdate(id, inputDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete by id")
    public ResponseEntity<VehicleDealerOutputDTO> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("VehicleDealerResource/deleteById(" + id + ") was called");
        VehicleDealerOutputDTO outputDTO = service.deleteById(id);
        return ResponseEntity.ok(outputDTO);
    }

}
