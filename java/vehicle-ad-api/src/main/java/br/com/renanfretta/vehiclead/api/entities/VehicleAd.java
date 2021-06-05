package br.com.renanfretta.vehiclead.api.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VehicleAd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 50)
    private String brand;

    @Column(length = 50)
    private String model;

    @Column(length = 4)
    private Short modelYear;

    @Column(length = 4)
    private Short manufacturingYear;

    @Column(length = 50)
    private String color;

    @Column(length = 11)
    private Integer mileage;

    private Boolean used;

    @ManyToOne
    @JoinColumn(name = "id_vehicle_dealer")
    private VehicleDealer vehicleDealer;

    @ManyToOne
    @JoinColumn(name = "id_vehicle_ad_state")
    private VehicleAdState state;

}
