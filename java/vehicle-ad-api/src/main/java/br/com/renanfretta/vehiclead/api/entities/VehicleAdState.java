package br.com.renanfretta.vehiclead.api.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VehicleAdState implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 2)
    private Short id;

    @Column(length = 50)
    private String name;

}
