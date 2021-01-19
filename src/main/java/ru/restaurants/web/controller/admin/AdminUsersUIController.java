package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;
import ru.restaurants.util.execption.ErrorInfo;

import javax.validation.Valid;
import java.util.List;

import static ru.restaurants.util.CheckedAdmin.checkedAdmin;
import static ru.restaurants.util.Convector.covertToUser;

@RestController
@RequestMapping(value = AdminUsersUIController.ADMIN_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUsersUIController {
    public static final String ADMIN_USERS = "/admin/users";

    @Autowired
    private final UserService userService;

    @Autowired
    private final VoteService voteService;

    public AdminUsersUIController(UserService userService, VoteService voteService) {
        this.userService = userService;
        this.voteService = voteService;
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
    public void delete (@PathVariable Integer id){
        checkedAdmin(id, userService);
        userService.delete(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ErrorInfo> createOrUpdate (@Valid ToUser toUser){
        checkedAdmin(toUser.id(), userService);
        User user = covertToUser(toUser, voteService);
        if (user.isNew()) {
            userService.create(user);
        } else {
            userService.update(user, user.id());
        }
        return ResponseEntity.ok().build();
    }

}
