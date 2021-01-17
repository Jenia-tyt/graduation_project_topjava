package ru.restaurants.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.util.execption.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurants.repository.UserDataTest.*;


class UserDateJpaRepositoryTest extends AbstractDataJpaTest{

    @Autowired
    UserService userService;

    @Test
    void duplicateCreateUser (){
        User user = userService.get(USER_ID_15);
        user.setId(USER_ID_66);
        assertThrows(DataAccessException.class, ()-> userService.create(user));
    }

    @Test
    void getNotFoundId (){
        assertThrows(NotFoundException.class, ()->userService.get(USER_ID_66));
    }

    @Test
    void deleteNotFoundId (){
        assertThrows(NotFoundException.class, ()->userService.delete(USER_ID_66));
    }

    @Test
    void get() {
        User user = userService.get(USER_ID_15);
        assertThat(user).isEqualTo(USER_WITH_ID_15);
    }

    @Test
    void getByEmail() {
        User user = userService.getByEmail("user@mail.ru");
        assertThat(user).isEqualTo(USER_WITH_ID_15);
    }

    @Test
    void delete() {
        userService.delete(USER_ID_15);
        assertThrows(NotFoundException.class, ()-> userService.get(USER_ID_15));
    }

    @Test
    void upDate() {
        User user = new User(USER_NEW_WITH_ID_66);
        user.setId(USER_ID_15);
        userService.upDate(user, user.id());
        assertThat(user).isEqualTo(userService.get(USER_ID_15));
    }

    @Test
    void create() {
        User user = userService.create(USER_NEW_WITH_ID_66);
        int id = user.id();
        assertThat(user).isEqualTo(userService.get(id));
    }

    @Test
    void getAll(){
        assertThat(ALL_USERS).isEqualTo(userService.getAll());
    }
}