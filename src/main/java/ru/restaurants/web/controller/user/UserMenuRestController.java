package ru.restaurants.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping(value = UserMenuRestController.USER_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuRestController {
    public static final String USER_MENU_TO_DAY = "/user/menuToDay";

    @Autowired
    private final MenuService service;

    public UserMenuRestController(MenuService menuService) {
        this.service = menuService;
    }

    @GetMapping("/")
    public List<Menu> getAllByDate() {
        LocalDate date = LocalDate.now();
        return service.getAllByDate(date);
    }

    @GetMapping("/{id}")
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return service.getAllMenuOfRest(id);
    }

    @PutMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String vote( Model model, @PathVariable int id ) {
        Menu m = service.get(id);
        Integer count = m.getRating() + 1;
        m.setRating(count);
        service.save(m);
        model.addAttribute("menu", service.getAllByDate(LocalDate.now()));
        return "menuToDay";
    }
}
