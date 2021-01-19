package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.util.execption.ErrorType;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_16;
import static ru.restaurants.util.Convector.covertToRestaurant;
import static ru.restaurants.util.execption.ErrorType.VALIDATION_ERROR;
import static ru.restaurants.web.TestUtil.readFromJson;
import static ru.restaurants.web.TestUtil.userHttpBasic;
import static ru.restaurants.web.controller.admin.AdminRestaurantRestController.RESTAURANT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurants.repository.RestDataTest.*;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String URL_ADMIN_REST = RESTAURANT + "/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Test
    void getAllRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN_REST)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Restaurant.class).contentJson(LIST_REST));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_REST + REST_ID)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Restaurant.class).contentJson(REST));
    }

    @Test
    void getNotFoundException() {
        assertThrows(NotFoundException.class, ()-> restaurantService.get(REST_ID_NOT_FOUND));
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_REST + REST_ID)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, ()-> restaurantService.delete(REST_ID));
    }

    @Test
    void save() throws Exception{
        ResultActions actions = perform(MockMvcRequestBuilders.post(URL_ADMIN_REST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_REST)));

        Restaurant createRest = readFromJson(actions, Restaurant.class);

        Restaurant restaurant = covertToRestaurant(NEW_TO_REST, menuService);
        restaurant.setId(createRest.getId());

        assertThat(restaurant).isEqualTo(createRest);
    }

    @Test
    void update() throws Exception{
        NEW_TO_REST.setId(REST_ID);

        perform(MockMvcRequestBuilders.put(URL_ADMIN_REST + REST_ID)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_REST)))
                .andExpect(status().isNoContent())
                .andDo(print());

        Restaurant restaurant = covertToRestaurant(NEW_TO_REST, menuService);
        assertThat(restaurant).isEqualTo(restaurantService.get(REST_ID));
    }

    @Test
    void updateHtmlUnsafeName() throws Exception {
        NEW_TO_REST.setName("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.post(URL_ADMIN_REST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_REST)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());

        NEW_TO_REST.setName("Новый ресторан");
    }

//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    void createRestaurantWithDuplicateName () throws Exception {
//        NEW_TO_REST.setName(restaurantService.get(1).getName());
//
//        perform(MockMvcRequestBuilders.post(URL_ADMIN_REST)
//                .with(userHttpBasic(USER_WITH_ID_16))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(NEW_TO_REST)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
//                .andDo(print());
//    }
}