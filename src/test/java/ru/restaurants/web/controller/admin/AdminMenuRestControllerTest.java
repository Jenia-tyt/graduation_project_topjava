package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.to.ToMenu;
import ru.restaurants.util.execption.ErrorType;
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
import static ru.restaurants.repository.UserDataTest.NEW_TO_USER;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_16;
import static ru.restaurants.util.Convector.covertToMenu;
import static ru.restaurants.util.execption.ErrorType.VALIDATION_ERROR;
import static ru.restaurants.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_DATE_MENU;
import static ru.restaurants.web.controller.admin.AdminMenuRestController.ADMIN_MENU_TO_DAY;
import static ru.restaurants.web.TestUtil.*;

class AdminMenuRestControllerTest extends AbstractControllerTest {
    private static final String URL_ADMIN_MENU_TO_DAY = ADMIN_MENU_TO_DAY + "/";

    @Autowired
    private MenuService service;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void getAllByDate() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN_MENU_TO_DAY)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_TO_DAY))
                .andDo(print());
    }

    @Test
    void getAllMenuForRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN_MENU_TO_DAY + REST_ID)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Menu.class).contentJson(MENU_OF_REST))
                .andDo(print());
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_MENU_TO_DAY + MENU_ID)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertThrows(NotFoundException.class, ()-> service.get(MENU_ID));
    }

    @Test
    void create() throws Exception{
        ResultActions actions = perform(MockMvcRequestBuilders.post(URL_ADMIN_MENU_TO_DAY)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_MENU)))
                .andDo(print());

        Menu create = readFromJson(actions, Menu.class);
        int id = create.id();
        NEW_TO_MENU.setId(id);

        Menu menu = covertToMenu(NEW_TO_MENU, restaurantService);

        assertThat(create).isEqualTo(menu);
        assertThat(create).isEqualTo(service.get(menu.id()));
    }

    @Test
    void update() throws Exception{
        ToMenu toMenu = NEW_TO_MENU;
        toMenu.setId(MENU_ID);
        perform(MockMvcRequestBuilders.put(URL_ADMIN_MENU_TO_DAY + MENU_ID)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(toMenu)))
                .andExpect(status().isNoContent())
                .andDo(print());

        Menu menu = covertToMenu(toMenu, restaurantService);
        assertThat(service.get(MENU_ID)).isEqualTo(menu);
    }

    @Test
    void notFoundExceptionDelete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_MENU_TO_DAY + NOT_FOUND_ID_MENU)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createMenuWithDuplicateDate () throws Exception {
        perform(MockMvcRequestBuilders.post(URL_ADMIN_MENU_TO_DAY)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MENU_DUPLICATE_DATE)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_DATE_MENU))
                .andDo(print());
    }

    @Test
    void createMenuWithEmptyMenu () throws Exception {
        NEW_TO_MENU.setMenuRest("");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_MENU_TO_DAY)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_MENU)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());

    }

    @Test
    void updateHtmlUnsafeEmail() throws Exception {
        NEW_TO_MENU.setMenuRest("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.post(URL_ADMIN_MENU_TO_DAY)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_MENU)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }
}