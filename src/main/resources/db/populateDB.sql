DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO restaurant (name, menu, rating)
VALUES  ('user1', 'компот 1 макороны 2 суп 3', 0),
        ('user2', 'чай 2 гречка 4 щи 9', 10),
        ('user3', 'кофе 3 кус кус 0.6 суп из семи залуп 4', 3),
        ('user5', 'вода 100 хлебная мякиш 2 вода с хлебными крошками 3', 99),
        ('user4', 'шампанское "кристал" 1 устрицы 1 черная икра 3', -10);

