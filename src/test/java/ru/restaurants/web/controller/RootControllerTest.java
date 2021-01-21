package ru.restaurants.web.controller;

import org.junit.jupiter.api.Test;
import ru.restaurants.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_16;
import static ru.restaurants.web.TestUtil.userAuth;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void root() throws Exception {
        perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"))
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"))
                .andDo(print());
    }

    @Test
    void logout() throws Exception {
        perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"))
                .andDo(print());
    }

    @Test
    void users() throws Exception {
        perform(get("/users")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andDo(print());
    }

    @Test
    void restaurant() throws Exception {
        perform(get("/restaurant")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurant.jsp"))
                .andDo(print());
    }

    @Test
    void menuToDay() throws Exception {
        perform(get("/menuToDay")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/menuToDay.jsp"))
                .andDo(print());
    }

    @Test
    void menusOfRestaurant() throws Exception {
        perform(get("/menusOfRestaurant")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/menusOfRestaurant.jsp"))
                .andDo(print());
    }

    @Test
    void register() throws Exception {
        perform(get("/register")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/register.jsp"))
                .andDo(print());
    }

    @Test
    void profile() throws Exception {
        perform(get("/profile")
                .with(userAuth(USER_WITH_ID_16)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/profile.jsp"))
                .andDo(print());
    }
}