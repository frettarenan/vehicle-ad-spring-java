package br.com.renanfretta.vehiclead.api.configs;

import br.com.renanfretta.vehiclead.api.commons.OrikaMapperBase;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapper extends OrikaMapperBase {

    public OrikaMapper() {
        if (mapperFacade != null)
            return;

        MapperFactory factory = new DefaultMapperFactory.Builder().build();

        factory.classMap(VehicleDealer.class, VehicleDealerOutputDTO.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("id", "id")
                .field("name", "name")
                .field("phone", "phone")
                .field("email", "email")
                .field("address", "address")
                .field("tierLimit", "tierLimit")
                .byDefault().register();

        factory.classMap(VehicleDealerInputDTO.class, VehicleDealer.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("name", "name")
                .field("phone", "phone")
                .field("email", "email")
                .field("address", "address")
                .field("tierLimit", "tierLimit")
                .byDefault().register();

        mapperFacade = factory.getMapperFacade();
    }

}
