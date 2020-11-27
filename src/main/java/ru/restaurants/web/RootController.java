package ru.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private RestaurantService serviceRest;

    @Autowired
    private MenuService serviceMenu;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/restaurants")
    public String getRest(Model model) {
        model.addAttribute("rest", serviceRest.getAll());
        return "restaurants";
    }

    @GetMapping("/menuToDay")
    public String getMenuToDay(Model model){
        LocalDate dateToDay = LocalDate.now();
        model.addAttribute("menu", serviceMenu.getAllDate(dateToDay));
        return "menuToDay";
    }
}
