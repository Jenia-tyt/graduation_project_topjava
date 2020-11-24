package ru.restaurants.repository;

import ru.restaurants.model.Role;
import ru.restaurants.model.User;


public class UserDataTest {
    public final static Integer USER_ID = 13;
    public final static User USER = new User(USER_ID, "user@mail.ru", "user", "passwordUser", null, false, Role.USER);
    public final static Integer USER_ID_66 = 66;
    public final static User USER_NEW_WITH_ID_66 = new User(USER_ID_66, "new@mai.ru", "newName", "newPassword", null, true, Role.ADMIN);

    public final static User USER_ID_15 = new User(15, "admin_user@gmail.com", "admin_user", "passwordAU", null, false, Role.USER);

}
