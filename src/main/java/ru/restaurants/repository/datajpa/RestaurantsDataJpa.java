package ru.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class RestaurantsDataJpa implements RestaurantRepository {

    @Autowired
    private final CrudRestaurant repository;

    public RestaurantsDataJpa(CrudRestaurant repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant save(Restaurant r) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Restaurant get(Integer id) {
        return repository.findById(id).orElse(null);

    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
