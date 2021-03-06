package ru.restaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Menu;
import ru.restaurants.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuDataJpaRepository implements MenuRepository {

    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "id");

    private final CrudMenu crudMenu;

    public MenuDataJpaRepository(CrudMenu crudMenu) {
        this.crudMenu = crudMenu;
    }

    @Override
    public List<Menu> getAll() {
        return crudMenu.findAll(SORT);
    }

    @Override
    public List<Menu> getAllMenuOfRest(int id_rest) {
        return crudMenu.getAllMenuOfRest(id_rest);
    }

    @Override
    public List<Menu> getAllDate(LocalDate date) {
        return crudMenu.getAllDate(date);
    }

    @Override
    public Menu get(int id) {
        return crudMenu.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudMenu.delete(id) != 0;
    }

    @Override
    public Menu save(Menu m) {
        return crudMenu.save(m);
    }

    public Menu getMenuWithIdDate(int id, LocalDate date){
        return crudMenu.getMenuWithIAndDate(id, date);
    }
}
