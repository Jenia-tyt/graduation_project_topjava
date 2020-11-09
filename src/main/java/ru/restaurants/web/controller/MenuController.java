package ru.restaurants.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.restaurants.model.Menu;
import ru.restaurants.repository.MenuRepository;

import java.util.List;

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
}
