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
import ru.restaurants.service.RestaurantService;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



@RestController
@RequestMapping(value = UserMenuRestController.USER_MENU_TO_DAY, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuRestController {
    public static final String USER_MENU_TO_DAY = "/user/menuToDay";
    public static LocalTime time_11_00 = LocalTime.of(11, 00, 00);


    @Autowired
    private final MenuService menuService;

    @Autowired
    private final UserService userService;


    @Autowired
    private final VoteService voteService;


    public UserMenuRestController(MenuService menuService, UserService userService, RestaurantService restaurantService, VoteService voteService) {
        this.menuService = menuService;
        this.userService = userService;
        this.voteService = voteService;
    }

    public static void setTime_11_00(LocalTime time_11_00) {
        UserMenuRestController.time_11_00 = time_11_00;
    }

    @GetMapping("/")
    public List<Menu> getAllByDate() {
        LocalDate date = LocalDate.now();
        return menuService.getAllByDate(date);
    }

    @GetMapping("/{id}")
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return menuService.getAllMenuOfRest(id);
    }

    //нужен рефакторинг много повторяющегося кода
    //нужно брать id из антификации юзера
    @PutMapping("/vote/{id}") //парметр id - id меню
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String vote( Model model, @PathVariable int id ) {
        LocalDateTime dateTime = LocalDateTime.now();

        Menu m = menuService.get(id);
        User user = userService.get(16);

        if (user.getVoteLast().isEqual(dateTime.toLocalDate())
                && user.getVoteLast() != null
                && dateTime.toLocalTime().isBefore(time_11_00)) {

            Vote vote = voteService.getVoteOfUserToDay(16, dateTime.toLocalDate()); //обрати захаркорен юзер

            voteService.delete(vote.id());                                          //удаляем старый голос

            Vote newVote = new Vote(null, user, dateTime.toLocalDate(), m.getRest().id());    //создаем в бд новый голос
            voteService.create(newVote);

            user.setVoteLast(dateTime.toLocalDate());               //надо отрефакторить мтеоды update они должны подваться с id
            userService.upDate(user);

            Menu oldMenu = menuService.getMenuWithIdRestAndDate(vote.getIdRest(), dateTime.toLocalDate());       //отзвать старый голос понижаем рейтинг на один
            oldMenu.setRating(oldMenu.getRating() - 1);
            menuService.upDate(oldMenu, oldMenu.id());

            m.setRating(m.getRating() + 1);     //кладем новый рейтинг
            menuService.upDate(m, m.id());

            model.addAttribute("menu", menuService.getAllByDate(LocalDate.now())); //кладем новую таблицу меню
            return "menuToDay";
        } else if (user.getVoteLast() == null || user.getVoteLast().isBefore(dateTime.toLocalDate())){ //добавить проверку чтор бы голосовать можно было за меню оторое сегоднешнее
            Vote newVote = new Vote(null, user, dateTime.toLocalDate(), m.getRest().id());
            voteService.create(newVote);

            user.setVoteLast(dateTime.toLocalDate());               //надо отрефакторить мтеоды update они должны подваться с id
            userService.upDate(user);

            m.setRating(m.getRating() + 1);
            menuService.upDate(m, m.id());

            model.addAttribute("menu", menuService.getAllByDate(LocalDate.now()));
            return "menuToDay";
        }   else {
                model.addAttribute("messages", "You voted to day");
            return "menuToDay";
        }
    }
}
