package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToRestaurant;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.restaurants.util.Convector.covertToRestaurant;


@RestController
@RequestMapping(value = AdminRestaurantRestController.RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {

    public static final String RESTAURANT = "/rest/admin/restaurant";

    @Autowired
    private final RestaurantService restService;

    @Autowired
    private final MenuService menuService;

    public AdminRestaurantRestController(RestaurantService restService, MenuService menuService) {
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> save(@RequestBody @Valid ToRestaurant toRest) {
        Restaurant restaurant = covertToRestaurant(toRest, menuService);
        Restaurant r = restService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT + "/{id}")
                .buildAndExpand(r.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(r);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody @Valid ToRestaurant toRest, @PathVariable int id){
        Restaurant rest = covertToRestaurant(toRest, menuService);
        restService.update(rest, id);
    }
}
