package ru.restaurants.repository;

import ru.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    public Restaurant save (Restaurant r);

    public boolean delete (Integer id);

    public Restaurant get (Integer id);

    public List<Restaurant> getAll ();
}
