package ru.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuDataJpaReposirory implements MenuRepository {

    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "name_rest");

    @Autowired
    private final CrudMenu crudMenu;

    public MenuDataJpaReposirory(CrudMenu crudMenu) {
        this.crudMenu = crudMenu;
    }

    @Override
    public List<Menu> getAll() {
        return crudMenu.findAll(SORT);
    }

    @Override
    public List<Menu> getAllRest(Restaurant r) {
        return null;
    }

    @Override
    public List<Menu> getAllDate(LocalDate date) {
        return null;
    }

    @Override
    public Menu get() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Menu save() {
        return null;
    }
}
