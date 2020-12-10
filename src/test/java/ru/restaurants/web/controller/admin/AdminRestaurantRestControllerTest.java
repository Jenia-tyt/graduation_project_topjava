package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.AbstractRestControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static ru.restaurants.web.TestUtil.readFromJson;
import static ru.restaurants.web.controller.admin.AdminRestaurantRestController.RESTAURANT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurants.repository.RestDataTest.*;

class AdminRestaurantRestControllerTest extends AbstractRestControllerTest {

    private static final String URL_ADMIN_REST = RESTAURANT + "/";

    @Autowired
    private RestaurantService service;

    @Test
    void getAllRest() throws Exception{
        perform(MockMvcRequestBuilders.get(URL_ADMIN_REST))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Restaurant.class).contentJson(LIST_REST));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_REST + REST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(Restaurant.class).contentJson(REST));
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_REST + REST_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, ()-> service.delete(REST_ID));
    }

    @Test
    void save() throws Exception{
        Restaurant r = REST;
        ResultActions actions = perform(MockMvcRequestBuilders.post(URL_ADMIN_REST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(r)));

        Restaurant createRest = readFromJson(actions, Restaurant.class);
        int id = createRest.id();
        r.setId(id);
        assertThat(r).isEqualTo(createRest);
        assertThat(createRest).isEqualTo(createRest);
    }

    @Test
    void upDate() throws Exception{
        Restaurant r = getNew();
        r.setId(REST_ID);

        perform(MockMvcRequestBuilders.put(URL_ADMIN_REST + REST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(r)))
                .andExpect(status().isNoContent());

        assertThat(r).isEqualTo(service.get(REST_ID));
    }
}