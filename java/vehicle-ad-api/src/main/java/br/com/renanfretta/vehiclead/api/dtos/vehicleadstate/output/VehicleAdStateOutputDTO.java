package br.com.renanfretta.vehiclead.api.dtos.vehicleadstate.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleAdStateOutputDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;

}
