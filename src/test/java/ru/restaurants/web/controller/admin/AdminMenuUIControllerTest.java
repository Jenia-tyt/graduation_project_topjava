package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_16;
import static ru.restaurants.web.TestUtil.userHttpBasic;

class AdminMenuUIControllerTest  extends AbstractControllerTest {
    private final static String URL_UI_ADMIN_MENU = "/admin/menuToDay";

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_UI_ADMIN_MENU)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andDo(print());
    }

    @Test
    void getAllMenuForRest() {
    }

    @Test
    void delete() {
    }

    @Test
    void createOrUpdateTO() {
    }

    @Test
    void upDate() {
    }


}