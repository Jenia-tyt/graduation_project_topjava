package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class DataTest {
    public static final Integer REST_ID = 1;
    public static final Restaurant REST = new Restaurant(REST_ID, "restaurant1", 0);
    public static final Integer REST_ID_NOT_FOUND = 22;
    public static final Integer MENU_ID = 6;
    public static final int NOT_ID_MENU = 999;
    public static final Menu MENU = new Menu(MENU_ID, REST, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "компот 1 макороны 2 суп 3");
    public static final Menu NEW_MENU = new Menu( REST, LocalDateTime.of(2044, Month.JANUARY, 1, 1, 00, 00), "новое меню");
    public static final LocalDateTime MENU_DATA = LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00);

    private static final Restaurant r1 = new Restaurant(REST_ID, "restaurant1", 0);
    private static final Restaurant r2 = new Restaurant(REST_ID+1,"restaurant2", 10);
    private static final Restaurant r3 = new Restaurant(REST_ID+2,"restaurant3", 3);
    private static final Restaurant r4 = new Restaurant(REST_ID+4,"restaurant4", -10);
    private static final Restaurant r5 = new Restaurant(REST_ID+3,"restaurant5",  99);
    public static final List<Restaurant> LIST_REST = List.of(r1, r2, r3, r4, r5);

    private static final Menu menu1 = new Menu (6, r1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "компот 1 макороны 2 суп 3");
    private static final Menu menu2 = new Menu (7, r1, LocalDateTime.of(2020, Month.JANUARY, 30, 11, 00, 00), "пиво 2 макороны 2 суп 3");
    private static final Menu menu3 = new Menu (8, r1, LocalDateTime.of(2020, Month.JANUARY, 20, 10, 00, 00), "сидр 3 макороны 2 суп 3");
    public static final List<Menu> MENU_OF_REST = List.of(menu1, menu2, menu3);

//    private static final Menu m1 = new Menu(6, r1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "компот 1 макороны 2 суп 3");
    private static final Menu m2 = new Menu(9, r2, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "чай 2 гречка 4 щи 9");
    private static final Menu m3 = new Menu(10, r3, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "кофе 3 кус кус 0.6 суп из семи залуп 4");
    private static final Menu m4 = new Menu(11, r5, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "вода 100 хлебная мякиш 2 вода с хлебными крошками 3");
    private static final Menu m5 = new Menu(12, r4, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 00, 00), "шампанское \"кристал\" 1 устрицы 1 черная икра 3");
    public static final List<Menu> ALL_MENU_OF_DATE_MENU_DATE = List.of(menu1, m2, m3, m4, m5);


    public static final List<Menu> ALL_MENU = List.of(menu1, m2, m3, m4, m5, menu2, menu3);

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
