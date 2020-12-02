package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant r);

    boolean delete (Integer id);

    Restaurant get (Integer id);

    List<Restaurant> getAll();



}
