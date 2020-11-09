package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantDataTest;
import ru.restaurants.util.execption.NotFoundException;
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
    public void duplicateCreateRestaurant(){
        assertThrows(DataAccessException.class, ()-> controller.save(new Restaurant(null, "restaurant1", 0)));
    }

    @Test
    public void deleteNotFounded(){
        assertThrows(NotFoundException.class, () -> controller.delete(RestaurantDataTest.REST_ID_NOT_FOUND));
    }

    @Test
    public void getNotFounded() {
        assertThrows(NotFoundException.class, () -> controller.get(RestaurantDataTest.REST_ID_NOT_FOUND));
    }

    @Test
    public void upDate(){
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        int id = r.id();
        Restaurant updateRest = RestaurantDataTest.getUpdate(r);
        updateRest.setId(id);
        controller.save(updateRest);
        Restaurant z = controller.get(id);
        assertThat(z).isEqualTo(updateRest);
    }

    @Test
    public void delete() {
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        controller.delete(RestaurantDataTest.REST_ID);
        Assert.assertThrows(NotFoundException.class, () -> controller.get(RestaurantDataTest.REST_ID));
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
        int idNew = r.id();
        Restaurant testR = RestaurantDataTest.getNew();
        testR.setId(idNew);
        Restaurant rBeforeSave = controller.get(idNew);
        assertThat(r).isEqualTo(testR);
        assertThat(r).isEqualTo(rBeforeSave);
    }
}
