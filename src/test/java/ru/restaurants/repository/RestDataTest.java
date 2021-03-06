package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.to.ToRestaurant;
import ru.restaurants.web.TestMatcher;

import java.util.ArrayList;
import java.util.List;

import static ru.restaurants.repository.MenuDataTest.MENU_OF_REST;

public class RestDataTest {
    public static final TestMatcher <Restaurant> REST_MATCHER_NOT_IGNORE = TestMatcher.usingEqualsComparator(Restaurant.class);
    public static final TestMatcher <Restaurant> REST_MATCHER_IGNORE_ID = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "id", "menu");

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

    public static final ToRestaurant NEW_TO_REST = new ToRestaurant(null, "Новый ресторан", new ArrayList<Menu>(), 0);
    public static final ToRestaurant TO_REST_DUPLICATED_NAME = new ToRestaurant(null, "restaurant1", new ArrayList<Menu>(), 0);
}
