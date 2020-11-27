package ru.restaurants.repository;

import ru.restaurants.model.Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {
    Menu get(int id);

    List<Menu> getAll();

    List<Menu> getAllMenuOfRest(int id_rest);

    List<Menu> getAllDate (LocalDate date);

    boolean delete (int id);

    Menu save (Menu m);

}
