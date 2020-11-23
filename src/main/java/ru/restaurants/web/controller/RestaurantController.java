package ru.restaurants.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;

import javax.transaction.Transactional;

import static ru.restaurants.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;


@Controller
public class RestaurantController {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(Integer id) {
        LOG.info("get restaurant {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }


    public Restaurant save(Restaurant r) {
        LOG.info("Save restaurant r{}", r);
        Assert.notNull(r, "Restaurant doesn't be null");
        return repository.save(r);
    }

    public List<Restaurant> getAll(){
        LOG.info("getAll restaurant");
        return repository.getAll();
    }

    public void delete (int id){
        LOG.info("delete restaurant id{}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Menu> getAllMenuForRest(Integer id){
        LOG.info("get all menu for restaurant id{}", id);
        return checkNotFoundWithId(repository.getAllMenuForRest(id), id);
    }

}