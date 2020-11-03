DROP TABLE IF EXISTS restaurant;

CREATE SEQUENCE global_seq START WITH 1;


CREATE TABLE restaurant
(
    id          INTEGER     PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR     unique not null,
    menu        VARCHAR     not null,
    rating      INTEGER
);
CREATE UNIQUE INDEX rest_unic_name_index ON restaurant (name)
