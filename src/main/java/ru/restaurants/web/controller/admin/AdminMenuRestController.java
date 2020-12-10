package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuRestController.ADMIN_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuRestController {

    public static final String ADMIN_MENU_TO_DAY = "/admin/menuToDay";

    @Autowired
    private final MenuService service;

    public AdminMenuRestController(MenuService service) {
        this.service = service;
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
    public ResponseEntity<Menu> create (@RequestBody Menu m){
        Menu menu = service.save(m);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_TO_DAY + "/{id}")
                .buildAndExpand(m.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(menu);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Menu m, @PathVariable int id){
        service.upDate(m, id);
    }

}
