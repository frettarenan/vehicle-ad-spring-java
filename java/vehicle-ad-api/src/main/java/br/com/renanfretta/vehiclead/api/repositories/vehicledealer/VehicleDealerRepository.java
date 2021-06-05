package br.com.renanfretta.vehiclead.api.repositories.vehicledealer;

import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDealerRepository extends JpaRepository<VehicleDealer, Long> {
}