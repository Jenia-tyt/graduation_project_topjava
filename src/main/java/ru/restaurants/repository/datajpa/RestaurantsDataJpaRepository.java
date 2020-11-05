package ru.restaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class RestaurantsDataJpaRepository implements RestaurantRepository {
    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurant crudRestaurant;

    public RestaurantsDataJpaRepository(CrudRestaurant repository) {
        this.crudRestaurant = repository;
    }

    @Override
    public Restaurant save(Restaurant r) {
       return crudRestaurant.save(r);
    }

    @Override
    public boolean delete(Integer id) {
        return crudRestaurant.delete(id) != 0;
    }

    @Override
    public Restaurant get(Integer id) {
        return crudRestaurant.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurant.findAll(SORT);
    }
}
