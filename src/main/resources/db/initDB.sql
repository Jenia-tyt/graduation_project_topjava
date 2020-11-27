DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;

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
--     date_menu   date        not null,
    date_menu   date        DEFAULT now(),
    menu        VARCHAR     not null,
    UNIQUE (id_rest, date_menu),
    FOREIGN KEY (id_rest) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id          INTEGER     PRIMARY KEY DEFAULT nextval('global_seq'),
    email       VARCHAR     UNIQUE NOT NULL,
    name        VARCHAR     UNIQUE NOT NULL ,
    password    VARCHAR     NOT NULL,
    vote_to_day bool        DEFAULT false NOT NULL
);
CREATE UNIQUE INDEX user_unic_index ON users (id);

CREATE TABLE role
(
    user_id     INTEGER     NOT NULL,
    role        VARCHAR     NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id          INTEGER     PRIMARY KEY DEFAULT nextval('global_seq'),
    id_user     INTEGER     NOT NULL,
    date_vote   TIMESTAMP   NOT NULL,
    id_rest     INTEGER     NOT NULL,

    UNIQUE (id_user, date_vote),
    FOREIGN KEY (id_rest) REFERENCES restaurant(id) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unic_index ON vote (id)