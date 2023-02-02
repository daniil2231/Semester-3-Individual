CREATE TABLE traders
(
    id int NOT NULL AUTO_INCREMENT,
    email varchar(45) NOT NULL,
    password varchar(100) NOT NULL,
    name_on_card varchar(45) NOT NULL,
    card_number varchar(45) NOT NULL,
    card_cvv varchar(45) NOT NULL,
    card_valid_thru varchar(45) NOT NULL,
    traded_volume double NOT NULL,
    realized_pnl double NOT NULL,
    funds double NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE positions
(
    id   int     NOT NULL AUTO_INCREMENT,
    traders_id int NULL,
    position_type varchar(45) NOT NULL,
    val double NOT NULL,
    entry_price double NOT NULL,
    liquidation_price double NOT NULL,
    change_in_price double NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (traders_id) REFERENCES traders (id)
);

CREATE TABLE users
(
    id int NOT NULL AUTO_INCREMENT,
    traders_id int NULL,
    email varchar(45) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    FOREIGN KEY (traders_id) REFERENCES traders (id)
);