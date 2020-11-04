package ru.restaurants.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;

import java.util.List;


@Controller
public class RestaurantController {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(Integer id) {
        LOG.info("get restaurant {}", id);
        return repository.get(id);
    }

    public Restaurant save(Restaurant r) {
        LOG.info("Save restaurant r{}", r);
        return repository.save(r);
    }

    public List<Restaurant> getAll(){
        LOG.info("getAll restaurant");
        return repository.getAll();
    }

    public void delete (int id){
        LOG.info("delete restaurant id{}", id);
        repository.delete(id);
    }
}
