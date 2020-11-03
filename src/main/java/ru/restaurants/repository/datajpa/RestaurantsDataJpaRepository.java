package ru.restaurants.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class RestaurantsDataJpaRepository implements RestaurantRepository {


    private final CrudRestaurant CrudRepository;

    public RestaurantsDataJpaRepository(CrudRestaurant repository) {
        this.CrudRepository = repository;
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
        return CrudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
