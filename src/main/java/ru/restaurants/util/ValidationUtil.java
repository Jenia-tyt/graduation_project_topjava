package ru.restaurants.util;

import javassist.NotFoundException;
import ru.restaurants.model.AbstractBaseEntity;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNew (AbstractBaseEntity entity) {
        if (!entity.isNew()){
            throw new IllegalArgumentException(entity + " id new entity must be null (id == null)");
        }
    }

    public static void assureIdConsistent (AbstractBaseEntity entity, int id){
        if (entity.isNew()){
            entity.setId(id);
        } else if (entity.getId() != id){
            throw new IllegalArgumentException(entity + " must be with id = "+ id);
        }
    }
}
