package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToRestaurant;
import ru.restaurants.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.restaurants.util.ValidationUtil.getErrorResponse;



@RestController
@RequestMapping(value = AdminRestaurantUIController.RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantUIController {

    public static final String RESTAURANT = "/admin/restaurant";

    @Autowired
    private final RestaurantService restService;

    @Autowired
    private final MenuService menuService;

    public AdminRestaurantUIController(RestaurantService restService, MenuService menuService) {
        this.restService = restService;
        this.menuService = menuService;
    }

    @GetMapping
    public List<Restaurant> getAllRest() {
        return restService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get (@PathVariable int id){
        return restService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        restService.delete(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate(@Valid ToRestaurant rest, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()){
            return getErrorResponse(result, request);
        }
        Restaurant restaurant = covertToRestaurant(rest);
        if (rest.isNew()){
            restService.save(restaurant);
        } else {
            restService.update(restaurant, rest.id());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Restaurant rest, @PathVariable int id){
        restService.update(rest, id);
    }

    private Restaurant covertToRestaurant (ToRestaurant rest){
        Restaurant restaurant;
        if (rest.isNew()){
            restaurant = new Restaurant(null, rest.getName(), null, 0);

        } else {
            restaurant = new Restaurant(rest.id(), rest.getName(), menuService.getAllMenuOfRest(rest.id()), rest.getRating());
        }

        return restaurant;
    }
}
