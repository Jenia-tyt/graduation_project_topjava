DELETE FROM restaurant;
DELETE FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO restaurant (name, rating)
VALUES  ('restaurant1', 0),
        ('restaurant2', 10),
        ('restaurant3', 3),
        ('restaurant5', 99),
        ('restaurant4', -10);

INSERT INTO menu (id_rest, date_menu, menu)
VALUES (1, '2020-01-30 10:00:00', 'компот 1 макороны 2 суп 3'),
       (1, '2020-01-30 11:00:00', 'пиво 2 макороны 2 суп 3'),
       (1, '2020-01-20 10:00:00', 'сидр 3 макороны 2 суп 3'),
        (2, '2020-01-30 10:00:00', 'чай 2 гречка 4 щи 9'),
        (3, '2020-01-30 10:00:00', 'кофе 3 кус кус 0.6 суп из семи залуп 4'),
        (4, '2020-01-30 10:00:00', 'вода 100 хлебная мякиш 2 вода с хлебными крошками 3'),
        (5, '2020-01-30 10:00:00', 'шампанское "кристал" 1 устрицы 1 черная икра 3');

