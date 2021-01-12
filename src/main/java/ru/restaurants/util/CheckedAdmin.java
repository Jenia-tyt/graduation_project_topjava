package ru.restaurants.util;

import ru.restaurants.model.User;
import ru.restaurants.service.UserService;
import ru.restaurants.util.execption.ErrorChange;

public class CheckedAdmin {
    public static void checkedAdmin(Integer id, UserService userService){
        User admin = userService.get(16);
        if (id.equals(admin.id())) {
            throw new ErrorChange(admin.getName());
        }
    }

    public static void checkedAdmin(Integer userId,  Integer adminId){
        if (userId.equals(adminId)) {
            throw new ErrorChange("ОШИБКА");
        }
    }
}
