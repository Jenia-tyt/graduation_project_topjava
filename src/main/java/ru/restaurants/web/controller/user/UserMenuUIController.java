package ru.restaurants.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.model.User;
import ru.restaurants.model.Vote;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.util.execption.ErrorInfo;
import ru.restaurants.util.execption.VoteException;

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

    @Autowired
    private final RestaurantService restaurantService;

    public UserMenuUIController(MenuService menuService, UserService userService, VoteService voteService, RestaurantService restaurantService) {
        this.menuService = menuService;
        this.userService = userService;
        this.voteService = voteService;
        this.restaurantService = restaurantService;
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

    @GetMapping("/all/{id}")
    public List<Menu> getAllMenuForRest(@PathVariable Integer id) {
        return menuService.getAllMenuOfRest(id);
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable Integer id) {
        return menuService.get(id);
    }

    @PutMapping("/vote/{id}")
    public ResponseEntity<ErrorInfo> vote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        LocalDateTime dateTime = LocalDateTime.now();

        Integer idUser = authorizedUser.getId();
        String nameUserAUth = authorizedUser.getUserTo().getName();

        Menu m = menuService.get(id);
        Menu oldMenu;

        User user = userService.get(idUser);

        Vote oldVote;
        Vote newVote;

        Restaurant restaurant = restaurantService.get(m.getRest().getId());
        Restaurant oldRestaurant;

        if (m.getDateMenu().isEqual(dateTime.toLocalDate())) {
            if (user.getVoteLast() == null || user.getVoteLast().isBefore(dateTime.toLocalDate())) {
                newVote = new Vote(null, user, dateTime.toLocalDate(), m.id());
                voteService.create(newVote);

                userService.updateLastVote(user, dateTime.toLocalDate());

                m.setRating(m.getRating() + 1);
                menuService.upDate(m, m.id());

                restaurant.setRating(restaurant.getRating() + 1);
                restaurantService.update(restaurant, restaurant.id());

                return new ResponseEntity<>(HttpStatus.OK);

            } else if (user.getVoteLast() != null
                    && (voteService.getVoteOfUserToDay(idUser, dateTime.toLocalDate()).getIdMenu()) != id
                    && user.getVoteLast().isEqual(dateTime.toLocalDate())
                    && dateTime.toLocalTime().isBefore(time_11_00)) {

                oldVote = voteService.getVoteOfUserToDay(idUser, dateTime.toLocalDate());

                voteService.delete(oldVote.id());

                newVote = new Vote(null, user, dateTime.toLocalDate(), m.id());
                voteService.create(newVote);

                userService.updateLastVote(user, dateTime.toLocalDate());

                oldMenu = menuService.getMenuWithIdDate(oldVote.getIdMenu(), dateTime.toLocalDate());
                oldMenu.setRating(oldMenu.getRating() - 1);
                menuService.upDate(oldMenu, oldMenu.id());

                oldRestaurant = restaurantService.get(oldMenu.getRest().id());
                oldRestaurant.setRating(oldRestaurant.getRating() - 1);
                restaurantService.update(oldRestaurant, oldRestaurant.id());

                restaurant.setRating(restaurant.getRating() + 1);
                restaurantService.update(restaurant, restaurant.id());

                m.setRating(m.getRating() + 1);
                menuService.upDate(m, m.id());


                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        throw new VoteException(nameUserAUth);
    }
}
