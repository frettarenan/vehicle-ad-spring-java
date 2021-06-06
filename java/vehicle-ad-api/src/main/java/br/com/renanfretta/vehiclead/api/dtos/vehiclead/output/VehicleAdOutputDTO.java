package br.com.renanfretta.vehiclead.api.dtos.vehiclead.output;

import br.com.renanfretta.vehiclead.api.dtos.vehicleadstate.output.VehicleAdStateOutputDTO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleAdOutputDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;
    private String brand;
    private String model;
    private Short modelYear;
    private Short manufacturingYear;
    private String color;
    private Integer mileage;
    private Boolean used;
    private VehicleAdStateOutputDTO state;
    private String createdAt;
    private String updatedAt;
    private String publishedAt;

}
