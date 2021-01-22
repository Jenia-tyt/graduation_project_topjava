package ru.restaurants.web.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.UserService;
import ru.restaurants.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.restaurants.repository.MenuDataTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.UserDataTest.*;
import static ru.restaurants.web.TestUtil.userHttpBasic;
import static ru.restaurants.web.controller.user.UserMenuRestController.USER_MENU_TO_DAY;
import static ru.restaurants.repository.RestDataTest.REST_ID;
import static ru.restaurants.web.controller.user.UserMenuRestController.*;


class UserMenuRestControllerTest extends AbstractControllerTest {
    private static final String URL_USER = USER_MENU_TO_DAY + "/";
    private static final String URL_VOTE = URL_USER + "vote/";

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Test
    void getAllByDate() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_USER)
                .with(userHttpBasic(USER_WITH_ID_15)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER_NOT_IGNORE.contentJson(MENU_TO_DAY))
                .andDo(print());
    }

    @Test
    void getAllMenuForRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_USER + REST_ID)
                .with(userHttpBasic(USER_WITH_ID_15)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER_NOT_IGNORE.contentJson(MENU_OF_REST))
                .andDo(print());
    }

    @Test

    void whoDidNotVoteToDay() throws Exception{
        Menu m = menuService.get(10);
        m.setDateMenu(LocalDate.now());
        Integer RatingRestaurantBeforeVoid = m.getRest().getRating();

        perform(MockMvcRequestBuilders.put(URL_VOTE + m.getId()).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_WITH_ID_17)))
                .andExpect(status().isOk())
                .andDo(print());

        assertThat(menuService.get(10).getRating()).isNotEqualTo(m3.getRating());
        assertThat(userService.get(USER_ID_17).getVoteLast()).isEqualTo(LocalDate.now());

        int rating = menuService.get(10).getRating();
        int difference = rating - (rating - 1);
        assertThat(difference).isEqualTo(1);

        Integer RatingRestaurantAfterVoid = m.getRest().getRating();
        int differenceRating = Math.abs(RatingRestaurantBeforeVoid - RatingRestaurantAfterVoid);
        assertThat(differenceRating).isEqualTo(1);
    }

    @Test
    void voidAfter11() throws Exception{
        setTime_11_00(LocalTime.now().minusHours(1));

        perform(MockMvcRequestBuilders.put(URL_VOTE + "10")
                .with(userHttpBasic(USER_WITH_ID_15)))
                .andExpect(status().isPreconditionFailed())
                .andDo(print());

        MENU_MATCHER_IGNORE_REST.assertMatch(menuService.get(10), m5);
        assertThat(menuService.get(10)).isEqualTo(m5);
        setTime_11_00(LocalTime.of(11,00,00));
    }

    @Test
    void voidBefore11() throws Exception {
        setTime_11_00(LocalTime.now().plusHours(1));

        Menu menuForWhichUserIsVote = menuService.get(14);
        int ratingOldMenu = menuForWhichUserIsVote.getRating();
        int ratingOldRestaurant = menuForWhichUserIsVote.getRest().getRating();

        Integer newMenuBeforeVote = menuService.get(13).getRating();

        perform(MockMvcRequestBuilders.put(URL_VOTE + "13")
                .with(userHttpBasic(USER_WITH_ID_15)))
                .andExpect(status().isOk())
                .andDo(print());

        Integer newMenuAfterVote = menuService.get(13).getRating();
        assertThat(newMenuBeforeVote).isNotEqualTo(newMenuAfterVote);

        int differentRatingMenu = Math.abs(newMenuAfterVote - newMenuBeforeVote);
        assertThat(differentRatingMenu).isEqualTo(1);

        Menu rollBackedMenu = menuService.get(14);
        int ratingReturnMenu = rollBackedMenu.getRating();
        int ratingReturnRestaurant = rollBackedMenu.getRest().getRating();

        int differentRatingOldMenu = Math.abs(ratingOldMenu - ratingReturnMenu);
        assertThat(differentRatingOldMenu).isEqualTo(1);
        int differentRatingOldRestaurant = Math.abs(ratingOldRestaurant - ratingReturnRestaurant);
        assertThat(differentRatingOldRestaurant).isEqualTo(1);

        setTime_11_00(LocalTime.of(11,00,00));
    }

    @Test
    void cantVoteBehindMenuWithDateIsNotToDay() throws Exception{ //переписать с тест матчером
        perform(MockMvcRequestBuilders.put(URL_USER + "vote/10")
                .with(userHttpBasic(USER_WITH_ID_15)))
                .andExpect(status().isPreconditionFailed())
                .andDo(print());

        MENU_MATCHER_IGNORE_REST.assertMatch(menuService.get(10), m5);
    }
}