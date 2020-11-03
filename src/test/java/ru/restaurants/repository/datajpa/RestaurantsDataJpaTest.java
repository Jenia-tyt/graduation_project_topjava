package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantDataTest;
import ru.restaurants.web.controller.RestaurantController;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringRunner.class)
public class RestaurantsDataJpaTest {

    @Autowired
    private RestaurantController controller;

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        assertThat(r).isEqualTo(RestaurantDataTest.REST);
    }

    @Test
    public void getAll() {
    }
}