package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToMenu;
import ru.restaurants.util.execption.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = AdminMenuUIController.ADMIN_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuUIController {

    public static final String ADMIN_MENU_TO_DAY = "/admin/menuToDay";

    @Autowired
    private final MenuService service;

    @Autowired
    private final RestaurantService restaurantService;


    public AdminMenuUIController(MenuService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping()
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

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ErrorInfo> createOrUpdateTO (@Valid ToMenu m){
        Menu menu = new Menu(m.getId(), restaurantService.get(m.getId_rest()), m.getDateMenu(), m.getMenuRest(), m.getRating());
        if (m.isNew()){
            service.save(menu);
        } else {
            service.upDate(menu, menu.id());
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Menu m, @PathVariable int id){
        service.upDate(m, id);
    }
}
