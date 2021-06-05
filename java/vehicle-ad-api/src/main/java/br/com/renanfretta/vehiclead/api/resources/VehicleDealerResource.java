package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.services.VehicleDealerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/vehicle-dealer")
public class VehicleDealerResource {

    private final VehicleDealerService service;

    @GetMapping
    public ResponseEntity<List<VehicleDealerOutputDTO>> findAll() {
        log.trace("VehicleDealerResource/findAll was called");
        List<VehicleDealerOutputDTO> list = service.findAll();
        if (list == null || list.isEmpty()) {
            log.trace("VehicleDealerResource/findAll noContent");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

}
