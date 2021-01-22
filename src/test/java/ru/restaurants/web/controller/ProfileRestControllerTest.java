package ru.restaurants.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.util.execption.ErrorType;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurants.repository.UserDataTest.*;
import static ru.restaurants.util.Convector.covertToUser;
import static ru.restaurants.web.TestUtil.userHttpBasic;

class ProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_REGISTER = "/rest/register";
    private static final String REST_UPDATE_PROFILE = "/rest/register/update";

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Test
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_UPDATE_PROFILE)
                .with(userHttpBasic(USER_WITH_ID_15))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(UPDATE_USER_WITH_ROLE_USER)))
                .andExpect(status().isNoContent())
                .andDo(print());

        User user = covertToUser(UPDATE_USER_WITH_ROLE_USER, voteService);
        user.setEmail(user.getEmail().toLowerCase());

        USER_TEST_MATCHER_IGNORE_ID_PASSWORD_VOTE.assertMatch(userService.get(USER_ID_15), user);
    }

    @Test
    void create() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER_2)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateWithDuplicatedName() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_UPDATE_PROFILE)
                .with(userHttpBasic(USER_WITH_ID_15))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ID_15_DUPLICATE_NAME_ADMIN)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateWithDuplicatedEmail() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_UPDATE_PROFILE)
                .with(userHttpBasic(USER_WITH_ID_15))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ID_15_DUPLICATE_EMAIL_ADMIN)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createWithDuplicatedName() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ID_15_DUPLICATE_NAME_ADMIN)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createWithDuplicatedEmail() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ID_15_DUPLICATE_EMAIL_ADMIN)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateWithAlertName() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_UPDATE_PROFILE)
                .with(userHttpBasic(USER_WITH_ID_15))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ALERT_NAME)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateWithAlertEmail() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_UPDATE_PROFILE)
                .with(userHttpBasic(USER_WITH_ID_15))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ALERT_EMAIL)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createWithAlertName() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ALERT_NAME)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createWithAlertEmail() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TO_USER_WITH_ALERT_NAME)))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }
}