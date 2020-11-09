package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    public List<Menu> getAll();

    public List<Menu> getAllRest(Restaurant r);

    public List<Menu> getAllDate (LocalDate date);

    public Menu get();

    public boolean delete (Integer id);

    public  Menu save ();

}
