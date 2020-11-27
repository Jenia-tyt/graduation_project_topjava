package ru.restaurants.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import ru.restaurants.model.Menu;
import ru.restaurants.repository.MenuRepository;


import java.time.LocalDate;
import java.util.List;

import static ru.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class MenuController {
    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private final MenuRepository repository;

    public MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    public List<Menu> getAll (){
        LOG.info("Get all menu");
        return repository.getAll();
    }

    public List<Menu> getAllMenuOfRest(int id_rest) {
        LOG.info("Get all menu for restaurant id{}", id_rest);
        return checkNotFoundWithId(repository.getAllMenuOfRest(id_rest), id_rest);
    }


    public List<Menu> getAllDate(LocalDate date) {
        LOG.info("get all menu with data{}", date.toString());
        return repository.getAllDate(date);
    }

    public Menu get(int id) {
        LOG.info("Get menu with id{}", id);
        return checkNotFoundWithId(repository.get(id),id);
    }

    public void delete(int id) {
        LOG.info("Delete menu with is{}", id);
        checkNotFoundWithId(repository.delete(id),id);
    }

    public Menu save(Menu m) {
        LOG.info("Save menu{}", m);
        Assert.notNull(m, "Id menu doen't be null");
        return repository.save(m);
    }
}
