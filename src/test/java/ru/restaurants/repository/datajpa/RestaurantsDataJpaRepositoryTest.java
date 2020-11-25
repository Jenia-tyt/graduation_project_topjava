package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.Restaurant;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.util.execption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurants.repository.MenuDataTest.*;
import static ru.restaurants.repository.RestDataTest.*;


public class RestaurantsDataJpaRepositoryTest extends AbstractDataJpaTest{

    @Autowired
    private RestaurantService service;

    @Test
    public void duplicateCreateRestaurant(){
        assertThrows(DataAccessException.class, ()-> service.save(new Restaurant(null, "restaurant1", 0)));
    }

    @Test
    public void deleteNotFounded(){
        assertThrows(NotFoundException.class, () -> service.delete(REST_ID_NOT_FOUND));
    }

    @Test
    public void getNotFounded() {
        assertThrows(NotFoundException.class, () -> service.get(REST_ID_NOT_FOUND));
    }

    @Test
    public void upDate(){
        Restaurant r = REST;
        r.setName("Name UpDate");
        r.addMenuInList(NEW_MENU);
        Restaurant z = service.save(r);
        assertThat(z).isEqualTo(r);
    }

    @Test
    public void delete() {
        service.delete(REST_ID);
        assertThrows(NotFoundException.class, () -> service.get(REST_ID));
    }

    @Test
    public void get() {
        Restaurant r = service.get(REST_ID);
        assertThat(r).isEqualTo(REST);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).isEqualTo(LIST_REST);
    }

    @Test
    public void create(){
        Restaurant r = service.save(getNew());
        int idNew = r.id();
        Restaurant testR = getNew();
        testR.setId(idNew);
        Restaurant rBeforeSave = service.get(idNew);
        assertThat(r).isEqualTo(testR);
        assertThat(r).isEqualTo(rBeforeSave);
    }

    @Test
    public void getAllMenuForRest(){
        assertThat(service.getAllMenuForRest(REST_ID))
                .usingElementComparatorIgnoringFields("id")
                .isEqualTo(MENU_OF_REST);
    }

    @Test
    public void save (){
        Restaurant r = getNew();
        Restaurant f = service.save(r);
        assertThat(r).isEqualTo(f);
    }

    @Test
    public void testCache() {
        List<Restaurant> allRest = service.getAll();
        service.delete(REST_ID);
        List<Restaurant> allRest2 = service.getAll();
        assertThat(allRest).isNotEqualTo(allRest2);
    }
}
