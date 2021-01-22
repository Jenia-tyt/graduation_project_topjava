package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.service.MenuService;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.MenuDataTest.*;
import static ru.restaurants.repository.RestDataTest.REST_ID;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_16;
import static ru.restaurants.web.TestUtil.userAuth;
import static ru.restaurants.web.TestUtil.userHttpBasic;

class AdminMenuUIControllerTest  extends AbstractControllerTest {
    private final static String URL_UI_ADMIN_MENU = "/admin/menuToDay/";

    @Autowired
    private MenuService menuService;

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_UI_ADMIN_MENU)
                .with(userAuth(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER_NOT_IGNORE.contentJson(MENU_TO_DAY))
                .andDo(print());
    }

    @Test
    void getAllMenuForRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_UI_ADMIN_MENU + REST_ID)
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER_NOT_IGNORE.contentJson(MENU_OF_REST))
                .andDo(print());
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(URL_UI_ADMIN_MENU + MENU_ID)
                .with(csrf().asHeader())
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertThrows(NotFoundException.class, ()-> menuService.get(MENU_ID));
    }

    @Test
    void outwithCSRFDelete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_UI_ADMIN_MENU + MENU_ID)
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void createOrUpdateToCREATE() throws Exception { //как пердать UI контроллер сущность
        perform(MockMvcRequestBuilders.post(URL_UI_ADMIN_MENU)
                .with(csrf().asHeader())
                .with(userAuth(USER_WITH_ID_16)))
//                .content(NEW_TO_MENU))
                .andDo(print());

    }

    @Test
    void upDate() {
    }


}