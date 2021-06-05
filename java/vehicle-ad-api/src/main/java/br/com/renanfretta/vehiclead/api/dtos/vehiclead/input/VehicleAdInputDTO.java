package br.com.renanfretta.vehiclead.api.dtos.vehiclead.input;

import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleAdInputDTO implements Serializable {

    private String brand;
    private String model;
    private Short modelYear;
    private Short manufacturingYear;
    private String color;
    private Integer mileage;
    private Boolean used;
    private VehicleDealer vehicleDealer;

}
