package br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDealerInputDTO implements Serializable {

    @Size(max = 100)
    @NotEmpty
    private String name;

    @Size(max = 50)
    @NotEmpty
    private String phone;

    @Size(max = 100)
    @NotEmpty
    private String email;

    @Size(max = 250)
    @NotEmpty
    private String address;

    @NotEmpty
    private Integer tierLimit;

}
