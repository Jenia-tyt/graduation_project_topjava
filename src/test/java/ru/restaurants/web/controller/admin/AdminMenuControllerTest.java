package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.MenuDataTest.*;
import static ru.restaurants.repository.RestDataTest.REST_ID;
import static ru.restaurants.web.controller.admin.AdminMenuRestController.ADMIN_MENU_TO_DAY;
import static ru.restaurants.web.TestUtil.*;

class AdminMenuControllerTest extends AbstractControllerTest {
    private static final String URL_ADMIN = ADMIN_MENU_TO_DAY + "/";

    @Autowired
    private MenuService service;

    @Test
    void getAllByDate() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_TO_DAY));
    }

    @Test
    void getAllMenuForRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN + REST_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_OF_REST));
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(URL_ADMIN + MENU_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, ()-> service.get(MENU_ID));
    }

    @Test
    void create() throws Exception{
        Menu newMenu = NEW_MENU;
        ResultActions actions = perform(MockMvcRequestBuilders.post(URL_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)));

        Menu create = readFromJson(actions, Menu.class);
        int id = create.id();
        newMenu.setId(id);
        assertThat(create).isEqualTo(NEW_MENU);
        assertThat(create).isEqualTo(service.get(newMenu.id()));
    }

    @Test
    void upDate() throws Exception{
        Menu menu = NEW_MENU;
        menu.setId(MENU_ID);
        perform(MockMvcRequestBuilders.put(URL_ADMIN + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menu)))
                .andExpect(status().isNoContent());

        assertThat(service.get(MENU_ID)).isEqualTo(menu);
    }

    @Test
    void notFoundExceptionDelete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_ADMIN + NOT_FOUND_ID_MENU).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}