package br.com.renanfretta.vehiclead.api.configs;

import br.com.renanfretta.vehiclead.api.commons.OrikaMapperBase;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdInsertInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicleadstate.output.VehicleAdStateOutputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.entities.VehicleAdState;
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

        factory.classMap(VehicleAdInsertInputDTO.class, VehicleAd.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("brand", "brand")
                .field("model", "model")
                .field("modelYear", "modelYear")
                .field("manufacturingYear", "manufacturingYear")
                .field("color", "color")
                .field("mileage", "mileage")
                .field("used", "used")
                .field("vehicleDealer", "vehicleDealer")
                .byDefault().register();

        factory.classMap(VehicleAdUpdateInputDTO.class, VehicleAd.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("brand", "brand")
                .field("model", "model")
                .field("modelYear", "modelYear")
                .field("manufacturingYear", "manufacturingYear")
                .field("color", "color")
                .field("mileage", "mileage")
                .field("used", "used")
                .byDefault().register();

        factory.classMap(VehicleAd.class, VehicleAdOutputDTO.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("brand", "brand")
                .field("model", "model")
                .field("modelYear", "modelYear")
                .field("manufacturingYear", "manufacturingYear")
                .field("color", "color")
                .field("mileage", "mileage")
                .field("used", "used")
                .field("state", "state")
                .field("createdAt", "createdAt")
                .field("updatedAt", "updatedAt")
                .field("publishedAt", "publishedAt")
                .byDefault().register();

        factory.classMap(VehicleAdState.class, VehicleAdStateOutputDTO.class)
                .constructorA().constructorB().mapNulls(true).mapNullsInReverse(true)
                .field("id", "id")
                .field("name", "name")
                .byDefault().register();

        mapperFacade = factory.getMapperFacade();
    }

}
