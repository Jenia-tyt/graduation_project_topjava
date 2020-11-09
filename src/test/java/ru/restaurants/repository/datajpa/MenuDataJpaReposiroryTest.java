package ru.restaurants.repository.datajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restaurants.model.Menu;
import ru.restaurants.web.controller.MenuController;
import ru.restaurants.web.controller.RestaurantController;

import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuDataJpaReposiroryTest {

    @Autowired
    private MenuController menuController;

    @Autowired
    private RestaurantController restaurantController;

    @Test
    public void getAll() {
        List<Menu> z = menuController.getAll();
        int f = 0;
    }

    @Test
    public void getAllRest() {
    }

    @Test
    public void getAllDate() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}