package ru.restaurants.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.restaurants.model.AbstractBaseEntity;
import ru.restaurants.util.execption.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T o, int id) {
        checkNotFoundWithId(o != null, id);
        return o;
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id = " + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Entity not founded with " + msg);
        }
    }


    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " id new entity must be null (id == null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must be with id = " + id);
        }
    }

    public static ResponseEntity<String> getErrorResponse(BindingResult result, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append("<br>");
        result.getFieldErrors().forEach(fe -> builder.append(String.format("[%s] %s", replaceNameField(fe.getField(), request), fe.getDefaultMessage())).append("<br><br>"));
        return new ResponseEntity<>(builder.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static String replaceNameField(String name, HttpServletRequest request) {
        if (request.getHeader("Accept-Language").startsWith("ru")) {
            return switch (name) {
                case ("dateMenu") -> "Дата меню";
                case ("name") -> "Имя";
                case ("menuRest") -> "Меню ресторана";
                case ("password") -> "Пароль";
                case ("email") -> "Email";
                default -> (name);
            };
        } else if (request.getHeader("Accept-Language").startsWith("en")) {
            return switch (name) {
                case ("dateMenu") -> "Date menu";
                case ("name") -> "Name";
                case ("menuRest") -> "Restaurant's menu";
                case ("password") -> "Password";
                case ("email") -> "Email";
                default -> (name);
            };
        }
        return name;
    }
}
