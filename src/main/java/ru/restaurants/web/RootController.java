package ru.restaurants.web;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.User;
import ru.restaurants.to.ToUser;


@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:menuToDay";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logout")
    public String logout(){
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

    @GetMapping("/menuToDay")
    public String menuToDay(){
        return "menuToDay";
    }

    @GetMapping("/menusOfRestaurant")
    public String menusOfRestaurant(){
        return "menusOfRestaurant";
    }

    @GetMapping("/register")
    public String register (){
        return "register";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser){
        model.addAttribute("toUser", authUser.getUserTo());
        return "profile";
    }
}
