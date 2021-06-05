package br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleDealerOutputDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Integer tierLimit;

}
