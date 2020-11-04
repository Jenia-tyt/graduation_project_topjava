package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantDataTest;
import ru.restaurants.web.controller.RestaurantController;


@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantsDataJpaTest {

    @Autowired
    private RestaurantController controller;

    @Test
    public void duplicateCreateRestaurant(){}

    @Test
    public void deleteNotFounded(){}

    @Test
    public void getNotFounded(){}

    @Test
    public void upDate(){}

    @Test
    public void delete() {
        controller.delete(RestaurantDataTest.REST_ID);
        Assert.assertNull(controller.get(RestaurantDataTest.REST_ID));
    }

    @Test
    public void get() {
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        assertThat(r).isEqualTo(RestaurantDataTest.REST);
    }

    @Test
    public void getAll() {
        assertThat(controller.getAll()).isEqualTo(RestaurantDataTest.LIST_REST);
    }

    @Test
    public void create(){
        Restaurant r = controller.save(RestaurantDataTest.getNew());
        int idNew = r.getId();
        Restaurant testR = RestaurantDataTest.getNew();
        testR.setId(idNew);
        Restaurant rBeforeSave = controller.get(idNew);
        assertThat(r).isEqualTo(testR);
        assertThat(r).isEqualTo(rBeforeSave);
    }
}
