package ru.restaurants.repository;

import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {
    public Menu get(int id);

    public List<Menu> getAll();

    public List<Menu> getAllMenuOfRest(int id_rest);

    public List<Menu> getAllDate (LocalDateTime date);

    public boolean delete (int id);

    public  Menu save (Menu m);

}
