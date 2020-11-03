package ru.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restaurants.model.Restaurant;

public interface CrudRestaurant extends JpaRepository<Restaurant, Integer> {

}
