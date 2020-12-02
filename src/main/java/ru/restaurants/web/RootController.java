package ru.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/restaurant")
    public String getRest(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("nameRest", serviceRest.get(id).getName());
        model.addAttribute("menu", serviceMenu.getAllMenuOfRest(id));
        return "restaurant";
    }

    @GetMapping("/menuToDay")
    public String getMenuToDay(Model model){
        LocalDate dateToDay = LocalDate.now();
        model.addAttribute("menu", serviceMenu.getAllByDate(dateToDay));
        return "menuToDay";
    }


}
