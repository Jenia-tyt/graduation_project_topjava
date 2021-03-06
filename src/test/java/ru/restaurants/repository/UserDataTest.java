package ru.restaurants.repository;

import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.to.ToUser;
import ru.restaurants.web.TestMatcher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class UserDataTest {
    public final static TestMatcher<User> USER_TEST_MATCHER_NOT_IGNORE = TestMatcher.usingEqualsComparator(User.class);
    public final static TestMatcher<User> USER_TEST_MATCHER_IGNORE_ID_PASSWORD_VOTE = TestMatcher.usingIgnoringFieldsComparator(User.class, "id", "password", "votes");

    public final static Integer USER_ID_15 = 15;
    public final static Integer USER_ID_17 = 17;
    public final static User USER_WITH_ID_15 = new User(USER_ID_15, "user@mail.ru", "user", "passwordUser", null, LocalDate.of(20, 01, 30), Role.USER);

    public final static Integer USER_ID_66 = 66;
    public final static User USER_NEW_WITH_ID_66 = new User(USER_ID_66, "new@mai.ru", "newName", "newPassword", null, LocalDate.of(20, 01, 30), Role.ADMIN);

    public final static User USER_WITH_ID_16 = new User(16, "admin@mail.ru", "admin", "passwordAdmin", null, LocalDate.of(20, 01, 30), Role.ADMIN);
    public final static User USER_WITH_ID_17 = new User(17, "admin_user@gmail.com", "admin_user", "passwordAU", null, LocalDate.of(20, 01, 30), Role.USER);

    public final static List<User> ALL_USERS = Arrays.asList(USER_WITH_ID_15, USER_WITH_ID_16, USER_WITH_ID_17);

    public final static ToUser NEW_TO_USER = new ToUser(null, "newToUser", null, "ToUser@mail.ru", "ToUserPassword", "USER");
    public final static ToUser NEW_TO_USER_2 = new ToUser(null, "newToUser2", null, "ToUser2@mail.ru", "ToUserPassword", "USER");
    public final static ToUser TO_USER_WITH_ID_15_DUPLICATE_NAME_ADMIN = new ToUser(15, "admin", null, "ToUser@mail.ru", "passwordUser", "USER");
    public final static ToUser TO_USER_WITH_ID_15_DUPLICATE_EMAIL_ADMIN = new ToUser(15, "toUser", null, "admin@mail.ru", "passwordUser", "USER");
    public final static ToUser UPDATE_USER = new ToUser(null, "UPDATE", LocalDate.now(), "UPDATE_USER@mail.ru", "UPDATE", "ADMIN");
    public final static ToUser UPDATE_USER_WITH_ROLE_USER = new ToUser(USER_ID_15, "UPDATE", LocalDate.now(), "UPDATE_USER@mail.ru", "UPDATE", "USER");
    public final static ToUser TO_USER_WITH_ALERT_NAME = new ToUser(null, "<script>alert(123)</script>", null, "ToUser@mail.ru", "ToUserPassword", "USER");
    public final static ToUser TO_USER_WITH_ALERT_EMAIL = new ToUser(null, "newToUser", null, "<script>alert(123)</script>", "ToUserPassword", "USER");

}
