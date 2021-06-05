package br.com.renanfretta.vehiclead.api.repositories.vehiclead;

import br.com.renanfretta.vehiclead.api.entities.VehicleAd;

import java.util.List;

public interface VehicleAdRepositoryCustom {

    List<VehicleAd> findByVehicleDealerAndState(Long vehicleDealerId, Short vehicleAdStateId);

}
