package ru.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restaurants.model.Menu;

public interface CrudMenu extends JpaRepository<Menu, Integer> {
}
