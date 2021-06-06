package br.com.renanfretta.vehiclead.api.repositories.vehiclead;

import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAdRepository extends VehicleAdRepositoryCustom, JpaRepository<VehicleAd, Long>, JpaSpecificationExecutor<VehicleAd>, QuerydslPredicateExecutor<VehicleAd> {
}
