package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.CustomResponseEntityExceptionHandler;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdInsertInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.exceptions.vehiclead.VehicleAdAlreadyPublishedException;
import br.com.renanfretta.vehiclead.api.exceptions.vehicledealer.VehicleDealerTierLimitExceededException;
import br.com.renanfretta.vehiclead.api.services.VehicleAdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/vehicle-ad")
public class VehicleAdResource {

    private final VehicleAdService service;
    private final ObjectMapperSpecialized objectMapper;

    @GetMapping
    @Operation(summary = "Find by vehicleDealerId and vehicleAdStateId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VehicleAdOutputDTO.class)))}),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomResponseEntityExceptionHandler.ErrorOutputDTO.class)))})
    })
    public ResponseEntity<List<VehicleAdOutputDTO>> findByVehicleDealerAndState(@RequestParam(name = "vehicleDealerId") Long vehicleDealerId,
                                                                                @RequestParam(name = "vehicleAdStateId", required = false) @Parameter(description="null: all, 1: draft and 2: published") Short vehicleAdStateId) {
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
    @Operation(summary = "Save new")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleAdOutputDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomResponseEntityExceptionHandler.ErrorOutputDTO.class)))})
    })
    public ResponseEntity<VehicleAdOutputDTO> save(@RequestBody VehicleAdInsertInputDTO inputDTO) {
        log.info("VehicleAdResource/save( " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleAdOutputDTO outputDTO = service.save(inputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(outputDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleAdOutputDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomResponseEntityExceptionHandler.ErrorOutputDTO.class)))})
    })
    public ResponseEntity<VehicleAdOutputDTO> updateAll(@PathVariable Long id, @RequestBody VehicleAdUpdateInputDTO inputDTO) throws ResourceNotFoundException {
        log.info("VehicleAdResource/updateAll(id: " + id + " obj: " + objectMapper.writeValueAsStringNoException(inputDTO) + ") was called");
        VehicleAdOutputDTO outputDTO = service.updateAll(id, inputDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @PatchMapping(value = "/{id}/publish")
    @Operation(summary = "Publish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleAdOutputDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomResponseEntityExceptionHandler.ErrorOutputDTO.class)))}),
            @ApiResponse(responseCode = "422", description = "Business rule", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomResponseEntityExceptionHandler.ErrorOutputDTO.class)))})
    })
    public ResponseEntity<VehicleAdOutputDTO> publish(@PathVariable Long id, @RequestParam(name = "respectLimit", required = false) @Parameter(description="true - false") String respectLimitStr) throws ResourceNotFoundException, VehicleAdAlreadyPublishedException, VehicleDealerTierLimitExceededException {
        boolean respectLimit = isNull(respectLimitStr) || !respectLimitStr.equalsIgnoreCase("false");
        log.info("VehicleAdResource/publish(id: " + id + " respectLimit: " + respectLimit + ") was called");
        VehicleAdOutputDTO outputDTO = service.publish(id, respectLimit);
        return ResponseEntity.ok(outputDTO);
    }

    @PatchMapping(value = "/{id}/unpublish")
    @Operation(summary = "Unpublish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleAdOutputDTO.class))}),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    })
    public ResponseEntity<VehicleAdOutputDTO> unpublish(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("VehicleAdResource/unpublish(" + id + ") was called");
        VehicleAdOutputDTO outputDTO = service.unpublish(id);
        return ResponseEntity.ok(outputDTO);
    }

}
