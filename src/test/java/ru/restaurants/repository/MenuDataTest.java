package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.to.ToMenu;
import ru.restaurants.web.TestMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.restaurants.repository.RestDataTest.*;


public class MenuDataTest {

    public static TestMatcher<Menu> MENU_MATCHER_NOT_IGNORE = TestMatcher.usingEqualsComparator(Menu.class);
    public static TestMatcher<Menu> MENU_MATCHER_IGNORE_REST = TestMatcher.usingIgnoringFieldsComparator(Menu.class, "rest");

    public static final Integer MENU_ID = 6;
    public static final int NOT_FOUND_ID_MENU = 999;
    public static final Menu MENU = new Menu(MENU_ID, REST, LocalDate.of(2020, Month.JANUARY, 30), "компот 10руб, макороны 20руб, суп 30руб", 0);
    public static final Menu NEW_MENU = new Menu( REST, LocalDate.of(2044, Month.JANUARY, 1), "новое меню", 0);
    public static final LocalDate MENU_DATA = LocalDate.of(2020, Month.JANUARY, 30);

    private static final Menu menu1 = new Menu (6, r1, LocalDate.of(2020, Month.JANUARY, 30), "компот 10руб, макороны 20руб, суп 30руб", 0);
    private static final Menu menu2 = new Menu (11, r1, LocalDate.of(2020, Month.JANUARY, 20), "сидр 30руб, макороны 20руб, суп 30руб", 1);
    private static final Menu menu3 = new Menu (12, r1, LocalDate.of(2020, Month.FEBRUARY, 29), "пиво 25руб, макороны 20руб, суп 30руб", 1);
    public static final List<Menu> MENU_OF_REST = List.of(menu1, menu2, menu3);

    public static final Menu m2 = new Menu(7, r2, LocalDate.of(2020, Month.JANUARY, 30), "чай 15руб, гречка 20руб, щи 40руб", 0);
    public static final Menu m3 = new Menu(8, r3, LocalDate.of(2020, Month.JANUARY, 30), "кофе 30руб, кус кус 15руб, суп грибной 40руб", 0);
    public static final Menu m4 = new Menu(9, r5, LocalDate.of(2020, Month.JANUARY, 30), "вода 5руб, хлебная мякиш 10руб, вода с хлебными крошками 15руб", 0);
    public static final Menu m5 = new Menu(10, r4, LocalDate.of(2020, Month.JANUARY, 30), "шампанское \"кристал\" 2руб, устрицы 2руб, черная икра 3руб", 0);
    public static final List<Menu> ALL_MENU_OF_DATE_MENU_DATE = List.of(menu1, m2, m3, m4, m5);

    public static final Menu m6 = new Menu(14, r4, LocalDate.now(), "шампанское \"кристал\" 2руб, устрицы 10руб, черная икра 30руб", 1);
    private static final Menu m7 = new Menu(13, r5, LocalDate.now(), "вода 10руб, хлебная мякиш 20руб, вода с хлебными крошками 30руб", 0);
    public static final List<Menu> ALL_MENU = List.of(menu1, m2, m3, m4, m5, menu2, menu3, m6, m7);

    public static final List<Menu> MENU_TO_DAY = List.of(m7, m6);

    public static final ToMenu NEW_TO_MENU = new ToMenu(null, REST_ID, LocalDate.now(), "Новое меню", 0);
    public static final ToMenu MENU_DUPLICATE_DATE = new ToMenu(null, REST_ID, LocalDate.of(2020, 01, 30), "Меню с дубликатом даты", 0);
}
