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
public class RootController {

    @GetMapping("/")
    public String root() {
        return "menuToDay";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users")
    public String users(){
        return "users";
    }

    @GetMapping("/restaurant")
    public String restaurant(){
        return "restaurant";
    }
}
