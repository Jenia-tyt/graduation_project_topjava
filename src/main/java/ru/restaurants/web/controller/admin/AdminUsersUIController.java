package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.util.ValidationUtil;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping(value = AdminUsersUIController.ADMIM_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUsersUIController {
    public static final String ADMIM_USERS = "/admin/users";

    @Autowired
    private final UserService userService;

    public AdminUsersUIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id){
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        userService.delete(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createOrUpdate (User user, BindingResult result){
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
        if (user.isNew()){
            userService.create(user);
        } else {
            userService.upDate(user, user.id());
        }
        return ResponseEntity.ok().build();
    }
}
