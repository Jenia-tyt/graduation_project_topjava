package ru.restaurants.web.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.controller.admin.AbstractRestControllerTest;

import static ru.restaurants.repository.MenuDataTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.web.controller.user.UserMenuRestController.USER_MENU_TO_DAY;
import static ru.restaurants.repository.RestDataTest.REST_ID;

class UserMenuRestControllerTest extends AbstractRestControllerTest {
    private static final String URL_USER = USER_MENU_TO_DAY + "/";

    @Autowired
    private MenuService service;

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

    @Test
    void vote() throws Exception{
        Menu m = service.get(MENU_ID);
        m.setRating(m.getRating() + 1);

        perform(MockMvcRequestBuilders.put(URL_USER + "vote/" + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(m).isEqualTo(service.get(MENU_ID));
    }
}