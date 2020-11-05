package ru.restaurants.util.execption;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mes){
        super(mes);
    }
}
