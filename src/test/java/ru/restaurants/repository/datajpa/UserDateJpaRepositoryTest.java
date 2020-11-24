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
        User user = userService.get(USER_ID);
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
        User user = userService.get(13);
        assertThat(user).isEqualTo(USER);
    }

    @Test
    void getByEmail() {
        User user = userService.getByEmail("user@mail.ru");
        assertThat(user).isEqualTo(USER);
    }

    @Test
    void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, ()-> userService.get(USER_ID));
    }

    @Test
    void upDate() {
        User user = new User(USER_NEW_WITH_ID_66);
        user.setId(USER_ID);
        userService.upDate(user);
        assertThat(user).isEqualTo(userService.get(USER_ID));
    }

    @Test
    void create() {
        User user = userService.create(USER_NEW_WITH_ID_66);
        int id = user.getId();
        assertThat(user).isEqualTo(userService.get(id));
    }
}