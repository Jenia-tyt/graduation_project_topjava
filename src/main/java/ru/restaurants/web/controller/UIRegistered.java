package ru.restaurants.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static ru.restaurants.util.Convector.covertToUser;

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

    @PostMapping("/update")
    public String update(@Valid ToUser toUser, BindingResult result, Model model, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authUser){
        if (result.hasErrors()){
            StringBuilder builder = new StringBuilder();
            result.getFieldErrors().forEach(fe -> builder.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            model.addAttribute("error", builder);
            return "/profile";
        }
        if (authUser.getUserTo().getId().equals(toUser.getId())){
            authUser.update(toUser);
            User user = covertToUser(toUser,voteService);
            userService.create(user);
            status.setComplete();
        } else {
            User user = covertToUser(toUser, voteService);
            userService.create(user);
        }
        return "redirect:/menuToDay";
    }

    @PostMapping()
    public String create (@Valid ToUser toUser, BindingResult result, Model model){
        if (result.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            result.getFieldErrors().forEach(fe -> builder.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            model.addAttribute("error", builder);
            return "register";
        } else {
            User user = covertToUser(toUser, voteService);
            userService.create(user);
            return "redirect:/login?message=app.registered";
        }
    }
}
