package ru.restaurants.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;

public class ErrorMessages {

     public static void messageErrorForEmailAndName(DataIntegrityViolationException e, BindingResult result){
        String message = e.getMessage();
        if (message != null) {
            if (message.contains("users_name_key")){
                result.rejectValue("name", "error.name");
            } else if (message.contains("users_email_key")) {
                result.rejectValue("email", "error.email");
            }
        }
    }
}
