package ru.restaurants.web.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.model.Menu;
import ru.restaurants.model.User;
import ru.restaurants.model.Vote;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping(value = UserMenuUIController.URL_UI_USER_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuUIController {
    public static final String URL_UI_USER_MENU_TO_DAY = "/profile/menuToDay";
    private static LocalTime time_11_00 = LocalTime.of(11, 00, 00);

    @Autowired
    private final MenuService menuService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final VoteService voteService;

    public UserMenuUIController(MenuService menuService, UserService userService, VoteService voteService) {
        this.menuService = menuService;
        this.userService = userService;
        this.voteService = voteService;
    }

    public static void setTime_11_00(LocalTime time_11_00) {
        UserMenuUIController.time_11_00 = time_11_00;
    }

    @GetMapping()
    public List<Menu> getAllByDate() {
        LocalDate date = LocalDate.now();
        return menuService.getAllByDate(date);
    }

    @GetMapping("/restaurant")
    public List<Menu> getAllMenuOfRest(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        return menuService.getAllMenuOfRest(id);
    }

    @GetMapping("/all/{id}") //добавить тест
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return menuService.getAllMenuOfRest(id);
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable Integer id) {
        return menuService.get(id);
    }

    //нужно брать id из антификации юзера
    //когда голусешь до 11 голосоа сумируются
    @PutMapping("/vote/{id}") //парметр id - id меню
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id ) {
        LocalDateTime dateTime = LocalDateTime.now();

        Menu m = menuService.get(id);
        Menu oldMenu;
        User user = userService.get(16);
        Vote vote;
        Vote newVote;

        if (m.getDateMenu().isEqual(dateTime.toLocalDate())) {
            if (user.getVoteLast() != null
                    && voteService.getVoteOfUserToDay(16, dateTime.toLocalDate()).id() != id //сравнить последний голос был за это же меню или нет
                    && user.getVoteLast().isEqual(dateTime.toLocalDate())
                    && dateTime.toLocalTime().isBefore(time_11_00)) {


                vote = voteService.getVoteOfUserToDay(16, dateTime.toLocalDate());

                voteService.delete(vote.id());

                newVote = new Vote(null, user, dateTime.toLocalDate(), m.getRest().id());
                voteService.create(newVote);

                user.setVoteLast(dateTime.toLocalDate());
                userService.upDate(user, user.id());

                oldMenu = menuService.getMenuWithIdRestAndDate(vote.getIdRest(), dateTime.toLocalDate());
                oldMenu.setRating(oldMenu.getRating() - 1);
                menuService.upDate(oldMenu, oldMenu.id());

                m.setRating(m.getRating() + 1);
                menuService.upDate(m, m.id());
            } else if (user.getVoteLast() == null || user.getVoteLast().isBefore(dateTime.toLocalDate())) {
                newVote = new Vote(null, user, dateTime.toLocalDate(), m.getRest().id());
                voteService.create(newVote);

                user.setVoteLast(dateTime.toLocalDate());
                userService.upDate(user, user.id());

                m.setRating(m.getRating() + 1);
                menuService.upDate(m, m.id());
            }
        }
    }
}
