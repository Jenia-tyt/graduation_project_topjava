package ru.restaurants.util.execption;

public class NotFoundExecptoin extends RuntimeException{
    public NotFoundExecptoin (String mes){
        super(mes);
    }
}
