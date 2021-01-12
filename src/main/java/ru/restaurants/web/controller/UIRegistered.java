package ru.restaurants.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToUser;

import javax.validation.Valid;

import static ru.restaurants.util.CheckedAdmin.checkedAdmin;
import static ru.restaurants.util.Convector.covertToUser;
import static ru.restaurants.util.ErrorMessages.messageErrorForEmailAndName;

@Controller
@RequestMapping(value = UIRegistered.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UIRegistered {
    public static final String URL = "/register";

    @Autowired
    private final VoteService voteService;

    @Autowired
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
        if (authUser.getUserTo().getId().equals(16)){
            result.rejectValue("name", "error.change.descriptionForFrom");
            result.rejectValue("email", "error.change.descriptionForFrom");
            result.rejectValue("password", "error.change.descriptionForFrom");
            return "/profile";
        } else if (authUser.getUserTo().getId().equals(toUser.getId())) {
            try {
                User user = covertToUser(toUser, voteService);
                userService.upDate(user, user.id());
                authUser.update(toUser);
                status.setComplete();
            } catch (DataIntegrityViolationException e) {
                messageErrorForEmailAndName(e, result);
                return "/profile";
            }
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
            try {
                User user = covertToUser(toUser, voteService);
                userService.create(user);
                return "redirect:/login?message=app.registered";
            } catch (DataIntegrityViolationException e){
                messageErrorForEmailAndName(e, result);
            }
        }
        return "register";
    }
}
