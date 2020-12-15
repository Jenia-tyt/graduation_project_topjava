package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.util.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuUIController.ADMIN_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuUIController {

    public static final String ADMIN_MENU_TO_DAY = "/admin/menuToDay";

    @Autowired
    private final MenuService service;

    public AdminMenuUIController(MenuService service) {
        this.service = service;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrUpdate (@RequestBody Menu m, BindingResult result){
        if (result.hasErrors()){
            return ValidationUtil.getErrorResponse(result);
        }
        if (m.isNew()){
            service.save(m);
        } else {
            service.upDate(m, m.id());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upDate(@RequestBody Menu m, @PathVariable int id){
        service.upDate(m, id);
    }

}
