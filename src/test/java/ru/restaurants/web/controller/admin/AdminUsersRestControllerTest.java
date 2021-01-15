package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.TestMatcher;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.UserDataTest.*;
import static ru.restaurants.util.Convector.covertToUser;

class AdminUsersRestControllerTest extends AbstractControllerTest {
    private static final String URL_ADMIN_USER_REST_TEST = "/rest/admin/users/";

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_USER_REST_TEST))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(User.class).contentJson(ALL_USERS))
                .andDo(print());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_USER_REST_TEST + USER_ID_15))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingEqualsComparator(User.class).contentJson(USER_WHIT_ID_15))
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_USER_REST_TEST +  USER_ID_15))
                .andExpect(status().isNoContent());

                assertThrows(NotFoundException.class, ()-> userService.delete(USER_ID_15));
    }

    @Test
    void create() throws Exception {
        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andDo(print());

        User createUser = userService.getByEmail(NEW_TO_USER.getEmail());
        NEW_TO_USER.setId(createUser.id());
        assertThat(createUser).isEqualTo(covertToUser(NEW_TO_USER, voteService));
    }

    //Добавить тест на апдейте
}