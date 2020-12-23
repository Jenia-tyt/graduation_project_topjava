package ru.restaurants.repository;

import ru.restaurants.model.Restaurant;

import java.util.List;

import static ru.restaurants.repository.MenuDataTest.MENU_OF_REST;

public class RestDataTest {
    public static final Integer REST_ID = 1;
    public static final Restaurant REST = new Restaurant(REST_ID, "restaurant1", null, 0);
    public static final Integer REST_ID_NOT_FOUND = 22;

    public static final Restaurant r1 = new Restaurant(REST_ID, "restaurant1", null,0);
    public static final Restaurant r2 = new Restaurant(REST_ID+1,"restaurant2",null, 10);
    public static final Restaurant r3 = new Restaurant(REST_ID+2,"restaurant3", null,3);
    public static final Restaurant r4 = new Restaurant(REST_ID+4,"restaurant4", null,-10);
    public static final Restaurant r5 = new Restaurant(REST_ID+3,"restaurant5", null, 99);
    public static final List<Restaurant> LIST_REST = List.of(r1, r2, r3, r4, r5);

    public static Restaurant getNew(){
        return new Restaurant(null, "getNew", null,666);
    }

    public static Restaurant getUpdate (Restaurant rest){
        Restaurant r = new Restaurant(rest);
        r.setId(null);
        r.setMenu(MENU_OF_REST);
        r.setName("upDateName");
        r.setRating(888);
        return r;
    }
}
