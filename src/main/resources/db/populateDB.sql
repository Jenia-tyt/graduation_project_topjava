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
VALUES  (1, '2020-01-30', 'компот 1 макороны 2 суп 3'),
        (1, '2020-01-20', 'сидр 3 макороны 2 суп 3'),
        (1, '2020-02-29', 'пиво 2 макороны 2 суп 3'),
        (2, '2020-01-30', 'чай 2 гречка 4 щи 9'),
        (3, '2020-01-30', 'кофе 3 кус кус 0.6 суп из семи залуп 4'),
        (4, '2020-01-30', 'вода 100 хлебная мякиш 2 вода с хлебными крошками 3'),
        (5, '2020-01-30', 'шампанское "кристал" 1 устрицы 1 черная икра 3'),
        (4, '2020-11-27', 'вода 100 хлебная мякиш 2 вода с хлебными крошками 3'),
        (5, '2020-11-27', 'шампанское "кристал" 1 устрицы 1 черная икра 3');

INSERT INTO users (email, name, password)
VALUES ('user@mail.ru', 'user', 'passwordUser'),
       ('admin@mail.ru', 'admin', 'passwordAdmin'),
       ('admin_user@gmail.com', 'admin_user', 'passwordAU');

INSERT INTO role (user_id, role)
VALUES (15, 'USER'),
       (16, 'ADMIN'),
       (17, 'ADMIN'),
       (17, 'USER');

INSERT INTO vote (id_user, date_vote, id_rest)
VALUES (15, '2020-01-30 13:00:00', 3),
       (17, '2020-01-30 10:00:00', 5)