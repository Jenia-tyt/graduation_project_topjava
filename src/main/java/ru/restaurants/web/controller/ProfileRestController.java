package ru.restaurants.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;

import javax.validation.Valid;

import java.net.URI;

import static ru.restaurants.util.Convector.covertToUser;
import static ru.restaurants.web.controller.ProfileRestController.URL_REST_PROFILE;
import static ru.restaurants.web.controller.admin.AdminUsersRestController.ADMIN_USERS_REST;


@RestController
@RequestMapping(value = URL_REST_PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController {
    public final static String URL_REST_PROFILE = "rest/register";

    @Autowired
    private final VoteService voteService;

    @Autowired
    private final UserService userService;

    public ProfileRestController(VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid ToUser toUser, @AuthenticationPrincipal AuthorizedUser authUser){
        if (authUser.getUserTo().getId().equals(toUser.getId())){
            User user = covertToUser(toUser, voteService);
            userService.upDate(user, user.id());
            authUser.update(toUser);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <User> create (@Valid @RequestBody ToUser toUser) {
        User user = covertToUser(toUser, voteService);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USERS_REST + "/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
