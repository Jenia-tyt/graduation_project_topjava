package ru.restaurants.util.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class VoteException extends RuntimeException{
    public VoteException (String mes){
        super(mes);
    }
}
