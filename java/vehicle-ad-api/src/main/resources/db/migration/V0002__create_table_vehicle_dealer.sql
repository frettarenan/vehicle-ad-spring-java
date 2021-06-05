CREATE TABLE vehicle_dealer (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    address varchar(250) NOT NULL,
    phone varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    tier_limit int(11) DEFAULT 0,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;