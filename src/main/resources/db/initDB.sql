DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;




CREATE TABLE restaurant
(
    id              INTEGER     PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR     unique not null,
    rating          INTEGER
);
CREATE UNIQUE INDEX rest_id_unic_index ON restaurant (id);


CREATE TABLE menu
(
    id          INTEGER     PRIMARY KEY DEFAULT  nextval('global_seq'),
    id_rest     INTEGER     not null,
    name_Rest   VARCHAR     not null,
    date_Menu   TIMESTAMP   not null,
    menu        VARCHAR     not null,
    UNIQUE (id_rest, name_Rest, date_Menu),
    FOREIGN KEY (id_rest) REFERENCES restaurant (id) ON DELETE CASCADE
)