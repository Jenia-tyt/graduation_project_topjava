package ru.restaurants.repository;

import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.to.ToUser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class UserDataTest {
    public final static Integer USER_ID_15 = 15;
    public final static User USER_WHIT_ID_15 = new User(USER_ID_15, "user@mail.ru", "user", "passwordUser", null, LocalDate.of(20, 01, 30), Role.USER);

    public final static Integer USER_ID_66 = 66;
    public final static User USER_NEW_WITH_ID_66 = new User(USER_ID_66, "new@mai.ru", "newName", "newPassword", null, LocalDate.of(20, 01, 30), Role.ADMIN);

    public final static User USER_ID_16 = new User(16, "admin@mail.ru", "admin", "passwordAdmin", null, LocalDate.of(20, 01, 30), Role.ADMIN);
    public final static User USER_ID_17 = new User(17, "admin_user@gmail.com", "admin_user", "passwordAU", null, LocalDate.of(20, 01, 30), Role.USER);

    public final static List<User> ALL_USERS = Arrays.asList(USER_WHIT_ID_15, USER_ID_16, USER_ID_17);

    public final static ToUser NEW_TO_USER = new ToUser(null, "newToUser", null, "ToUser@mail.ru", "ToUserPassword", "USER");

}
