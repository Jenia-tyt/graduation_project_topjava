package ru.restaurants.util;

import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;

import java.time.LocalDate;

public class UpdateDate {

    public static void updateDate(MenuService menuService, LocalDate date) {
        Menu menu1 = menuService.get(13);
        Menu menu2 = menuService.get(14);
        
        menu1.setDateMenu(date);
        menu2.setDateMenu(date);

        menuService.upDate(menu1, menu1.id());
        menuService.upDate(menu2, menu2.id());
    }
}
