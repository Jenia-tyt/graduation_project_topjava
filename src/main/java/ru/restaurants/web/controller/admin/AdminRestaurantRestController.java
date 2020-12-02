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

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping(AdminRestaurantRestController.RESTAURANT)
public class AdminRestaurantRestController {

    public static final String RESTAURANT = "/admin/restaurant";

    @Autowired
    private final RestaurantService restService;

    @Autowired
    private final MenuService menuService;

    public AdminRestaurantRestController(RestaurantService restService, MenuService menuService) {
        this.restService = restService;
        this.menuService = menuService;
    }

    @GetMapping("/")
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
    public ResponseEntity<Restaurant> save(@RequestBody Restaurant rest) {
        Restaurant r = restService.save(rest);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT + "/{id}")
                .buildAndExpand(r.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(r);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Restaurant rest, @PathVariable int id){
        restService.update(rest, id);
    }
}
