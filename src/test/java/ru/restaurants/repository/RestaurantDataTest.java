package ru.restaurants.repository;

import ru.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantDataTest {
    public static final Integer REST_ID = 1;
    public static final Restaurant REST = new Restaurant(REST_ID, "user1", "компот 1 макороны 2 суп 3", 0);

    private static final Restaurant r1 = new Restaurant(REST_ID, "user1", "компот 1 макороны 2 суп 3", 0);
    private static final Restaurant r2 = new Restaurant(REST_ID+1,"user2", "чай 2 гречка 4 щи 9", 10);
    private static final Restaurant r3 = new Restaurant(REST_ID+2,"user3", "кофе 3 кус кус 0.6 суп из семи залуп 4", 3);
    private static final Restaurant r4 = new Restaurant(REST_ID+4,"user4", "шампанское \"кристал\" 1 устрицы 1 черная икра 3", -10);
    private static final Restaurant r5 = new Restaurant(REST_ID+3,"user5", "вода 100 хлебная мякиш 2 вода с хлебными крошками 3", 99);
    public static final List<Restaurant> LIST_REST = List.of(r1, r2, r3, r4, r5);

    public static Restaurant getNew(){
        return new Restaurant(null, "getNew", "menu", 666);
    }
}
