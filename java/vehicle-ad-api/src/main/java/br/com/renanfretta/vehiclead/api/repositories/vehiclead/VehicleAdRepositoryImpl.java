package br.com.renanfretta.vehiclead.api.repositories.vehiclead;

import br.com.renanfretta.vehiclead.api.entities.QVehicleAd;
import br.com.renanfretta.vehiclead.api.entities.QVehicleAdState;
import br.com.renanfretta.vehiclead.api.entities.QVehicleDealer;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.enums.VehicleAdStateEnum;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Repository
public class VehicleAdRepositoryImpl implements VehicleAdRepositoryCustom {

    private final EntityManager em;

    private static final QVehicleAd _vehicleAd = QVehicleAd.vehicleAd;
    private static final QVehicleDealer _vehicleDealer = QVehicleDealer.vehicleDealer;
    private static final QVehicleAdState _vehicleAdState = QVehicleAdState.vehicleAdState;

    @Override
    public List<VehicleAd> findByVehicleDealerAndState(Long vehicleDealerId, Short vehicleAdStateId) {
        JPAQuery<VehicleAd> query = new JPAQuery<>(em);

        query.from(_vehicleAd);
        query.join(_vehicleAd.vehicleDealer, _vehicleDealer);
        query.join(_vehicleAd.state, _vehicleAdState);

        query.where(_vehicleDealer.id.eq(vehicleDealerId));

        if (nonNull(vehicleAdStateId))
            query.where(_vehicleAdState.id.eq(vehicleAdStateId));

        if (nonNull(vehicleAdStateId) && vehicleAdStateId.equals(VehicleAdStateEnum.PUBLISHED.getId()))
            query.orderBy(_vehicleAd.publishedAt.desc());
        else
            query.orderBy(_vehicleAd.createdAt.desc());

        return query.fetch();
    }

}
