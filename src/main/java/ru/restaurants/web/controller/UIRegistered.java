package ru.restaurants.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = UIRegistered.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UIRegistered {
    public static final String URL = "/register";

    private final VoteService voteService;

    private final UserService userService;

    public UIRegistered(VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
    }

    @PostMapping()
    public String create (@Valid ToUser toUser, BindingResult result, Model model){
        if (result.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            result.getFieldErrors().forEach(fe -> builder.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            model.addAttribute("error", builder);
            return "register";
        }
        User user = covertToUser(toUser);

        userService.create(user);

        return "redirect:/login";
    }

    private User covertToUser (ToUser u){
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
