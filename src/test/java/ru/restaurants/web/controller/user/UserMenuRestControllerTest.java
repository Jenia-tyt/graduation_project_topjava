package ru.restaurants.web.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Menu;
import ru.restaurants.model.User;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.UserService;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.AbstractRestControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.restaurants.repository.MenuDataTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.web.controller.user.UserMenuRestController.USER_MENU_TO_DAY;
import static ru.restaurants.repository.RestDataTest.REST_ID;
import static ru.restaurants.web.controller.user.UserMenuRestController.time_11_00;

class UserMenuRestControllerTest extends AbstractRestControllerTest {
    private static final String URL_USER = USER_MENU_TO_DAY + "/";

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Test
    void getAllByDate() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_TO_DAY));
    }

    @Test
    void getAllMenuForRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_USER + REST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_OF_REST));
    }

    /*
    1. возможность проголосовать (рейтинг должен увеличиться) +
    2. отказ если голосуешь после 11 +
    3. если уже голосуешь, но решил переголосовать (старый рейтин меню откатиться назад, новый прибавиться)

     */
    @Test
    void whoDidNotVoteToDay() throws Exception{
        perform(MockMvcRequestBuilders.put(URL_USER+ "vote/10"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());

        assertThat(menuService.get(10)).isNotEqualTo(m3);
        assertThat(userService.get(16).getVoteLast()).isEqualTo(LocalDate.now());
        int rating = menuService.get(10).getRating();
        int difference = rating - (rating - 1);
        assertThat(difference).isEqualTo(1);
    }

    @Test
    void voidAfter11() throws Exception{ //отредоктирвать тест что бы работал всега а не только после 11
        User user = userService.get(16);
        user.setVoteLast(LocalDate.now());

        perform(MockMvcRequestBuilders.put(URL_USER+ "vote/10"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());

        assertThat(menuService.get(10)).isEqualTo(m3);
    }

    @Test
    void voidBefore11() throws Exception { //нужно прголосовать, потом проголосовать еще раз и отозвать свое придыдущий голос

        perform(MockMvcRequestBuilders.put(URL_USER + "vote/14"));
        Menu menu11AfterVote = menuService.get(14);
        assertThat(m6).isNotEqualTo(menu11AfterVote);

        time_11_00 = LocalTime.now().plusHours(1);

        perform(MockMvcRequestBuilders.put(URL_USER + "vote/10"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());

        assertThat(m6).isEqualTo(menuService.get(14));
        assertThat(m3).isNotEqualTo(menuService.get(10));
        time_11_00 = LocalTime.of(11, 00, 00);
    }
    //добавить тест, на то что нельзя голосовать за меню у которого дата не сегодня
}