package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToMenu;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurants.util.Convector.covertToMenu;

@RestController
@RequestMapping(value = AdminMenuRestController.ADMIN_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuRestController {

    public static final String ADMIN_MENU_TO_DAY = "/rest/admin/menuToDay";

    @Autowired
    private final MenuService service;

    @Autowired
    private final RestaurantService restaurantService;

    public AdminMenuRestController(MenuService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Menu> getAllByDate() {
        LocalDate date = LocalDate.now();
        return service.getAllByDate(date);
    }

    @GetMapping("/{id}")
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return service.getAllMenuOfRest(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create (@RequestBody @Valid ToMenu toMenu){
        Menu covertToMenu = covertToMenu(toMenu, restaurantService);
        Menu menu = service.save(covertToMenu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_TO_DAY + "/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ToMenu toMenu, @PathVariable int id){
        Menu menu = covertToMenu(toMenu, restaurantService);
        service.upDate(menu, id);
    }
}
