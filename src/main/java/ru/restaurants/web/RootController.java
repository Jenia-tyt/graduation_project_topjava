package ru.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.service.RestaurantService;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private RestaurantService service;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/restaurants")
    public String getRest(Model model) {
        model.addAttribute("rest", service.getAll());
        return "restaurants";
    }


    @PostMapping("/sot")
    public String redirect(Model model) {
//        model.addAttribute("rest", service.getAll());
        return "redirect:restaurants";
    }
}
