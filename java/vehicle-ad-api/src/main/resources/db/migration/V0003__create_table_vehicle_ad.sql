CREATE TABLE vehicle_ad (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    brand varchar(50) NOT NULL,
    color varchar(50) NOT NULL,
    manufacturing_year int(4) NOT NULL,
    mileage int(11) NOT NULL,
    model varchar(50) NOT NULL,
    model_year int(4) NOT NULL,
    used bit(1) NOT NULL,
    id_vehicle_ad_state int(2) NOT NULL,
    id_vehicle_dealer bigint(20) NOT NULL,
    PRIMARY KEY (id),
    KEY FK_VEHICLE_AD_STATE (id_vehicle_ad_state),
    KEY FK_VEHICLE_AD_DEALER (id_vehicle_dealer),
    CONSTRAINT FK_VEHICLE_AD_STATE FOREIGN KEY (id_vehicle_ad_state) REFERENCES vehicle_ad_state (id),
    CONSTRAINT FK_VEHICLE_AD_DEALER FOREIGN KEY (id_vehicle_dealer) REFERENCES vehicle_dealer (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;