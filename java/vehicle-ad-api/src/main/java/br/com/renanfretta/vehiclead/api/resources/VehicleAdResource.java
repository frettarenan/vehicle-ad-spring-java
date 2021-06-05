package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.services.VehicleAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/vehicle-ad")
public class VehicleAdResource {

    private final VehicleAdService service;

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

}
