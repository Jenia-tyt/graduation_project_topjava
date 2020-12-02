package ru.restaurants.web.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.MenuService;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.controller.admin.AbstractRestControllerTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.as;
import static ru.restaurants.repository.MenuDataTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    }
}