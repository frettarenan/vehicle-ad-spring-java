package br.com.renanfretta.vehiclead.api.dtos.vehiclead.input;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Short modelYear;

    @NotNull
    private Short manufacturingYear;

    @Size(max = 50)
    @NotEmpty
    private String color;

    @NotNull
    private Integer mileage;

    @NotNull
    private Boolean used;

}
