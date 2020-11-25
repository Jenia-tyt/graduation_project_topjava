package ru.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;
import ru.restaurants.web.controller.RestaurantController;

import java.util.List;

import static ru.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(Integer id) {
        LOG.info("get restaurant {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant save(Restaurant r) {
        LOG.info("Save restaurant r{}", r);
        Assert.notNull(r, "Restaurant doesn't be null");
        return repository.save(r);
    }

    @Cacheable("restaurant")
    public List<Restaurant> getAll(){
        LOG.info("getAll restaurant");
        return repository.getAll();
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public void delete (int id){
        LOG.info("delete restaurant id{}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Menu> getAllMenuForRest(Integer id){
        LOG.info("get all menu for restaurant id{}", id);
        return checkNotFoundWithId(repository.getAllMenuForRest(id), id);
    }
}
