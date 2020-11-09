package ru.restaurants.repository;

import ru.restaurants.model.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantDataTest {
    public static final Integer REST_ID = 1;
    public static final Restaurant REST = new Restaurant(REST_ID, "restaurant1", 0);
    public static final Integer REST_ID_NOT_FOUND = 22;

    private static final Restaurant r1 = new Restaurant(REST_ID, "restaurant1", 0);
    private static final Restaurant r2 = new Restaurant(REST_ID+1,"restaurant2", 10);
    private static final Restaurant r3 = new Restaurant(REST_ID+2,"restaurant", 3);
    private static final Restaurant r4 = new Restaurant(REST_ID+4,"restaurant4", -10);
    private static final Restaurant r5 = new Restaurant(REST_ID+3,"restaurant5",  99);
    public static final List<Restaurant> LIST_REST = List.of(r1, r2, r3, r4, r5);

    public static Restaurant getNew(){
        return new Restaurant(null, "getNew", 666);
    }

    public static Restaurant getUpdate (Restaurant rest){
        Restaurant r = new Restaurant(rest);
        r.setId(null);
        r.setName("restaurant1");
        r.setRating(888);
        return r;
    }
}
