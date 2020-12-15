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
import ru.restaurants.service.RestaurantService;
import ru.restaurants.util.ValidationUtil;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = AdminRestaurantUIController.RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantUIController {

    public static final String RESTAURANT = "/admin/restaurant";

    @Autowired
    private final RestaurantService restService;

    public AdminRestaurantUIController(RestaurantService restService) {
        this.restService = restService;
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
    public ResponseEntity<String> createOrUpdate(@RequestBody Restaurant rest, BindingResult result) {
        if (result.hasErrors()){
            return ValidationUtil.getErrorResponse(result);
        }
        if (rest.isNew()){
            restService.save(rest);
        } else {
            restService.update(rest, rest.id());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Restaurant rest, @PathVariable int id){
        restService.update(rest, id);
    }
}
