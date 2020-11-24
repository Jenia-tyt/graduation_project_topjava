package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.Restaurant;
import ru.restaurants.repository.DataTest;
import ru.restaurants.service.RestaurantService;
import ru.restaurants.util.execption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;



public class RestaurantsDataJpaRepositoryTest extends AbstractDataJpaTest{

    @Autowired
    private RestaurantService service;

    @Test
    public void duplicateCreateRestaurant(){
        assertThrows(DataAccessException.class, ()-> service.save(new Restaurant(null, "restaurant1", 0)));
    }

    @Test
    public void deleteNotFounded(){
        assertThrows(NotFoundException.class, () -> service.delete(DataTest.REST_ID_NOT_FOUND));
    }

    @Test
    public void getNotFounded() {
        assertThrows(NotFoundException.class, () -> service.get(DataTest.REST_ID_NOT_FOUND));
    }

    @Test
    public void upDate(){
        Restaurant r = DataTest.REST;
        r.setName("Name UpDate");
        r.addMenuInList(DataTest.NEW_MENU);
        Restaurant z = service.save(r);
        assertThat(z).isEqualTo(r);
    }

    @Test
    public void delete() {
        service.delete(DataTest.REST_ID);
        assertThrows(NotFoundException.class, () -> service.get(DataTest.REST_ID));
    }

    @Test
    public void get() {
        Restaurant r = service.get(DataTest.REST_ID);
        Restaurant f = DataTest.REST;
        assertThat(r).isEqualTo(DataTest.REST);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).isEqualTo(DataTest.LIST_REST);
    }

    @Test
    public void create(){
        Restaurant r = service.save(DataTest.getNew());
        int idNew = r.id();
        Restaurant testR = DataTest.getNew();
        testR.setId(idNew);
        Restaurant rBeforeSave = service.get(idNew);
        assertThat(r).isEqualTo(testR);
        assertThat(r).isEqualTo(rBeforeSave);
    }

    @Test
    public void getAllMenuForRest(){
        assertThat(service.getAllMenuForRest(DataTest.REST_ID))
                .usingElementComparatorIgnoringFields("id")
                .isEqualTo(DataTest.MENU_OF_REST);
    }

    @Test
    public void save (){
        Restaurant r = DataTest.getNew();
        Restaurant f = service.save(r);
        assertThat(r).isEqualTo(f);
    }
}
