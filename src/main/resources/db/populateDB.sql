DELETE FROM restaurant;
DELETE FROM menu;
DELETE FROM users;
DELETE FROM role;
DELETE FROM vote;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO restaurant (name, rating)
VALUES  ('restaurant1', 0),
        ('restaurant2', 10),
        ('restaurant3', 3),
        ('restaurant5', 99),
        ('restaurant4', -10);


INSERT INTO menu (id_rest, date_menu, menu)
VALUES  (1, '2020-01-30', 'компот 10руб, макороны 20руб, суп 30руб'),
        (2, '2020-01-30', 'чай 15руб, гречка 20руб, щи 40руб'),
        (3, '2020-01-30', 'кофе 30руб, кус кус 15руб, суп грибной 40руб'),
        (4, '2020-01-30', 'вода 5руб, хлебная мякиш 10руб, вода с хлебными крошками 15руб'),
        (5, '2020-01-30', 'шампанское "кристал" 2руб, устрицы 2руб, черная икра 3руб');

INSERT INTO menu (id_rest, date_menu, menu, rating)
VALUES  (1, '2020-01-20', 'сидр 30руб, макороны 20руб, суп 30руб', 1),
        (1, '2020-02-29', 'пиво 25руб, макороны 20руб, суп 30руб', 1);


INSERT INTO menu (id_rest, menu)
VALUES  (4, 'вода 10руб, хлебная мякиш 20руб, вода с хлебными крошками 30руб');

INSERT INTO menu (id_rest, menu, rating)
VALUES (5, 'шампанское "кристал" 2руб, устрицы 10руб, черная икра 30руб', 1);

INSERT INTO users (email, name, password, vote_last)
VALUES ('user@mail.ru', 'user', '{noop}passwordUser', now()),
       ('admin@mail.ru', 'admin', '{noop}passwordAdmin', '2020-01-30'),
       ('admin_user@gmail.com', 'admin_user', '{noop}passwordAU', '2020-01-30');

INSERT INTO role (user_id, role)
VALUES (15, 'USER'),
       (16, 'ADMIN'),
       (17, 'ADMIN'),
       (17, 'USER');

INSERT INTO vote (id_user, date_vote, id_menu)
VALUES (15, '2020-01-30', 11),
       (17, '2020-01-30', 12);

INSERT INTO vote (id_user, id_menu)
VALUES (15, 14);
