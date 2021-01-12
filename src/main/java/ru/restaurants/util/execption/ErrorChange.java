package ru.restaurants.util.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ErrorChange extends RuntimeException{
    public ErrorChange(String message) {
        super(message);
    }
}
