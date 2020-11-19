package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.RestaurantDataTest;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.controller.RestaurantController;

import java.util.List;


@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantsDataJpaTest {

    @Autowired
    private RestaurantController controller;

    @ClassRule
    public static ExternalResource summary = TimingRule.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRule.STOPWATCH;

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
    @Transactional
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
    public void delete() { //NOT OK, Need check, does menu with this ID be in table with name "menu".
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        controller.delete(RestaurantDataTest.REST_ID);
        Assert.assertThrows(NotFoundException.class, () -> controller.get(RestaurantDataTest.REST_ID));
    }

    @Test
    public void get() {
        Restaurant r = controller.get(RestaurantDataTest.REST_ID);
        Restaurant f = RestaurantDataTest.REST;
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

    @Test
    public void getAllMenuForRest(){
        assertThat(controller.getAllMenuForRest(RestaurantDataTest.REST_ID))
                .usingElementComparatorIgnoringFields("id")
                .isEqualTo(RestaurantDataTest.MENU_OF_REST);
    }

    @Test
    @Transactional
    public void save (){
        Restaurant r = RestaurantDataTest.getNew();
        r.setMenu(RestaurantDataTest.MENU_OF_REST);
        Restaurant f = controller.save(r);
        assertThat(r).isEqualTo(f);
    }
}
