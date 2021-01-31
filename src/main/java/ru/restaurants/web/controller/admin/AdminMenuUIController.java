package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToMenu;
import ru.restaurants.util.execption.ErrorInfo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurants.util.Convector.covertToMenu;
import static ru.restaurants.util.UpdateDate.updateDate;


@RestController
@RequestMapping(value = AdminMenuUIController.ADMIN_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuUIController {

    public static final String ADMIN_MENU_TO_DAY = "/admin/menuToDay";

    @Autowired
    private final MenuService menuService;

    @Autowired
    private final RestaurantService restaurantService;


    public AdminMenuUIController(MenuService service, RestaurantService restaurantService) {
        this.menuService = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public List<Menu> getAllByDate() {
        LocalDate date = LocalDate.now();
        updateDate(menuService, date);
        return menuService.getAllByDate(date);
    }

    @GetMapping("/{id}")
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return menuService.getAllMenuOfRest(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        menuService.delete(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ErrorInfo> createOrUpdateTO (@Valid ToMenu m){
        Menu menu = covertToMenu(m, restaurantService);
        if (m.isNew()){
            menuService.save(menu);
        } else {
            menuService.upDate(menu, menu.id());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Menu m, @PathVariable int id){
        menuService.upDate(m, id);
    }
}
