package ru.restaurants.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.service.VoteService;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.AbstractControllerTest;
import ru.restaurants.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurants.repository.UserDataTest.*;
import static ru.restaurants.util.Convector.covertToUser;
import static ru.restaurants.util.execption.ErrorType.VALIDATION_ERROR;
import static ru.restaurants.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;
import static ru.restaurants.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_NAME;
import static ru.restaurants.web.TestUtil.readFromJson;
import static ru.restaurants.web.TestUtil.userHttpBasic;

class AdminUsersRestControllerTest extends AbstractControllerTest {
    private static final String URL_ADMIN_USER_REST_TEST = "/rest/admin/users/";

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_TEST_MATCHER_NOT_IGNORE.contentJson(ALL_USERS))
                .andDo(print());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_TEST_MATCHER_NOT_IGNORE.contentJson(USER_WITH_ID_15))
                .andDo(print());
    }

    @Test
    void getNoFoundException() {
        assertThrows(NotFoundException.class, ()-> userService.get(USER_ID_66));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_ADMIN_USER_REST_TEST +  USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16)))
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, ()-> userService.delete(USER_ID_15));
    }

    @Test
    void create() throws Exception {
        NEW_TO_USER.setName("newToUser");
        User user = covertToUser(NEW_TO_USER, voteService);
        user.setEmail(user.getEmail().toLowerCase());

        ResultActions actions = perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(USER_TEST_MATCHER_IGNORE_ID_PASSWORD_VOTE.contentJson(user))
                .andDo(print());

        User createUser = readFromJson(actions, User.class);
        user.setId(createUser.getId());

        USER_TEST_MATCHER_NOT_IGNORE.assertMatch(user, createUser);
    }

    @Test
    void update () throws Exception {
        UPDATE_USER.setId(USER_ID_15);

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(UPDATE_USER)))
                .andDo(print());

        User updateUser = covertToUser(UPDATE_USER, voteService);
        USER_TEST_MATCHER_NOT_IGNORE.assertMatch(updateUser, userService.get(USER_ID_15));
    }

    @Test
    void createUserWithBadNameException () throws Exception {
        NEW_TO_USER.setName("A");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createUserWithEmptyNameException () throws Exception {
        NEW_TO_USER.setName("");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createUserWithBadEmailException() throws Exception {
        NEW_TO_USER.setEmail("badEmail");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createUserWithEmptyEmailException() throws Exception {
        NEW_TO_USER.setEmail("");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void createUserWithEmptyPasswordException() throws Exception {
        NEW_TO_USER.setPassword("AD");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createUserWithDuplicateName() throws Exception {
        NEW_TO_USER.setName("admin");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_NAME))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createUserWithDuplicateEmail() throws Exception {
        NEW_TO_USER.setName("NEW_TO_USER");
        NEW_TO_USER.setEmail("admin@mail.ru");

        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_EMAIL))
                .andDo(print());

    }

    @Test
    void updateUserWithBadNameException () throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("A");
        NEW_TO_USER.setEmail("user@mail.ru");


        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateUserWithEmptyNameException () throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("");
        NEW_TO_USER.setEmail("user@mail.ru");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateUserWithBadEmailException() throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("");
        NEW_TO_USER.setEmail("usermail.ru");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateUserWithEmptyEmailException() throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("user");
        NEW_TO_USER.setEmail("");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateUserWithEmptyPasswordException() throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("user");
        NEW_TO_USER.setEmail("user@mail.ru");
        NEW_TO_USER.setPassword("AD");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }



    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateUserWithDuplicateName() throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("admin");
        NEW_TO_USER.setEmail("user@mail.ru");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_NAME))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateUserWithDuplicateEmail() throws Exception {
        NEW_TO_USER.setId(USER_ID_15);
        NEW_TO_USER.setName("user");
        NEW_TO_USER.setEmail("admin@mail.ru");

        perform(MockMvcRequestBuilders.put(URL_ADMIN_USER_REST_TEST + USER_ID_15)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_EMAIL))
                .andDo(print());
    }

    @Test
    void updateHtmlUnsafeName() throws Exception {
        NEW_TO_USER.setName("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateHtmlUnsafeEmail() throws Exception {
        NEW_TO_USER.setEmail("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.post(URL_ADMIN_USER_REST_TEST)
                .with(userHttpBasic(USER_WITH_ID_16))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(NEW_TO_USER)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }
}