package ru.restaurants.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;
import ru.restaurants.util.ValidationUtil;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;


@RestController
@RequestMapping(value = AdminUsersUIController.ADMIM_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUsersUIController {
    public static final String ADMIM_USERS = "/admin/users";

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
    public void delete (@PathVariable int id){
        userService.delete(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate (ToUser toUser, BindingResult result){
        if (result.hasErrors()) {
//            return ValidationUtil.getErrorResponse(result);
            StringBuilder builder = new StringBuilder();
            result.getFieldErrors().forEach(fe -> builder.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(builder.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        User user = covertToUser(toUser);

        if (user.isNew()){
            userService.create(user);
        } else {
            userService.upDate(user, user.id());
        }
        return ResponseEntity.ok().build();
    }

    private User covertToUser (@Valid ToUser u){
        String [] array = u.getRole().split("\\.");
        Set<Role> roleSet = new HashSet<>();
        for (String s : array) {
            switch (s) {
                case ("USER"):
                    roleSet.add(Role.USER);
                    break;
                case ("ADMIN"):
                    roleSet.add(Role.ADMIN);
                    break;
                default:
                    break;
            }
        }

        User user;
        if (u.isNew()) {
             user = new User(null, u.getEmail(), u.getName(), u.getPassword(), null, u.getVoteLast(), roleSet);
        }
        else {
            user = new User(u.id(), u.getEmail(), u.getName(), u.getPassword(), voteService.getAllVoteByUser(u.id()), u.getVoteLast(), roleSet); //сюда дабавить все голосо пользователя
        }
        return user;
    }
}
