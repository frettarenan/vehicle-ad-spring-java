package br.com.renanfretta.vehiclead.api.dtos.vehiclead.input;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleAdUpdateInputDTO implements Serializable {

    @Size(max = 50)
    @NotEmpty
    private String brand;

    @Size(max = 50)
    @NotEmpty
    private String model;

    @NotEmpty
    private Short modelYear;

    @NotEmpty
    private Short manufacturingYear;

    @Size(max = 50)
    @NotEmpty
    private String color;

    private Integer mileage;

    @NotEmpty
    private Boolean used;

}
