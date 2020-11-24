package ru.restaurants.util;

import ru.restaurants.model.AbstractBaseEntity;
import ru.restaurants.util.execption.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T o, int id){
        checkNotFoundWithId(o != null, id);
        return o;
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id){
        checkNotFound(found, "id = " + id);
    }

    public static void checkNotFound(boolean found, String msg){
        if (!found){
            throw new NotFoundException("Entity not founded with " + msg);
        }
    }


    public static void checkNew (AbstractBaseEntity entity) {
        if (!entity.isNew()){
            throw new IllegalArgumentException(entity + " id new entity must be null (id == null)");
        }
    }

    public static void assureIdConsistent (AbstractBaseEntity entity, int id){
        if (entity.isNew()){
            entity.setId(id);
        } else if (entity.id() != id){
            throw new IllegalArgumentException(entity + " must be with id = "+ id);
        }
    }
}
