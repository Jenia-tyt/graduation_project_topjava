package ru.restaurants.web.json;

import org.junit.jupiter.api.Test;
import ru.restaurants.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.restaurants.repository.UserDataTest.USER_WITH_ID_15;

class JsonUtilTest {

    @Test
    void readValues() {
        String userJson = JsonUtil.writeValue(USER_WITH_ID_15);
        System.out.println(userJson);
        User user = JsonUtil.readValue(userJson, User.class);

    }

    @Test
    void readValue() {
    }

    @Test
    void  writeValue(){
    }

}