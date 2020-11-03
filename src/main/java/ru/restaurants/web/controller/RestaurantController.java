package ru.restaurants.web.controller;

import jdk.jfr.Unsigned;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantRepository;


@Controller
public class RestaurantController {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    @Qualifier("RestaurantsDataJpa")
    private RestaurantRepository repository;


    public Restaurant get (Integer id){
        LOG.info("get restaurant {}", id);
        return repository.get(id);
    }
}
