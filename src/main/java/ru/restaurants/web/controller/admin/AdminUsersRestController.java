package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.restaurants.util.Convector.covertToUser;
import static ru.restaurants.web.controller.admin.AdminUsersRestController.ADMIN_USERS_REST;

@RestController
@RequestMapping(value = ADMIN_USERS_REST, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUsersRestController {
    public static final String ADMIN_USERS_REST = "rest/admin/users";

    @Autowired
    private final UserService userService;

    @Autowired
    private final VoteService voteService;

    public AdminUsersRestController(UserService userService, VoteService voteService) {
        this.userService = userService;
        this.voteService = voteService;
    }

    @GetMapping()
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create ( @RequestBody @Valid ToUser toUser){
        User user = covertToUser(toUser, voteService);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USERS_REST).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void update (@RequestBody @Valid ToUser toUser, @PathVariable Integer id){
        User user = covertToUser(toUser, voteService);
        userService.update(user, id);
    }
}

