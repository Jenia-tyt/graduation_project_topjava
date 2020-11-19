package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.time.LocalDateTime;
import java.time.Month;
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

    private static final Menu menu1 = new Menu ( "restaurant1", r1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "компот 1 макороны 2 суп 3");
    private static final Menu menu2 = new Menu ("restaurant1", r1, LocalDateTime.of(2020, Month.JANUARY, 30, 11, 00, 00), "пиво 2 макороны 2 суп 3");
    private static final Menu menu3 = new Menu ("restaurant1", r1, LocalDateTime.of(2020, Month.JANUARY, 20, 10, 00, 00), "сидр 3 макороны 2 суп 3");
    public static final List<Menu> MENU_OF_REST = List.of(menu1, menu2, menu3);

    public static Restaurant getNew(){
        return new Restaurant(null, "getNew", 666);
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
