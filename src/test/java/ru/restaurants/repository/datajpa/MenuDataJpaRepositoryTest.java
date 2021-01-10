package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.AbstractBaseEntity;
import ru.restaurants.model.Menu;
import ru.restaurants.service.MenuService;
import ru.restaurants.util.execption.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurants.repository.MenuDataTest.*;
import static ru.restaurants.repository.RestDataTest.*;

public class MenuDataJpaRepositoryTest extends AbstractDataJpaTest{

    @Autowired
    MenuService service;

    @Test
    void duplicateCreateMenu(){
        Menu m = new Menu(MENU);
        m.setId(null);
        assertThrows(DataAccessException.class, () -> service.save(m));

    }

    @Test
    void deleteNotFounded(){
        assertThrows(NotFoundException.class, ()-> service.delete(NOT_FOUND_ID_MENU));
    }

    @Test
    void getNotFounded(){
        assertThrows(NotFoundException.class, ()-> service.get(NOT_FOUND_ID_MENU));
    }

    @Test
    void getAll() {
        List<Menu> allMenu = service.getAll();
        allMenu.sort(Comparator.comparing(AbstractBaseEntity::id));
        List<Menu> z = new ArrayList<>(ALL_MENU);
        z.sort(Comparator.comparing(AbstractBaseEntity::id));
        assertThat(allMenu).isEqualTo(z);
    }

    @Test
    void getAllMenuOfRest() {
        List<Menu> allMenuOfRest = service.getAllMenuOfRest(REST_ID);
        assertThat(allMenuOfRest).isEqualTo(MENU_OF_REST);
    }

    @Test
    void getAllDate() {
        List<Menu> allMenuOfDate = service.getAllByDate(MENU_DATA);
        allMenuOfDate.sort(Comparator.comparing(m -> m.getRest().getName()));
        List<Menu> z = new ArrayList<>(ALL_MENU_OF_DATE_MENU_DATE);
        z.sort(Comparator.comparing(m -> m.getRest().getName()));
        assertThat(z).isEqualTo(allMenuOfDate);
    }

    @Test
    void get() {
        assertThat(MENU).isEqualTo(service.get(MENU_ID));
    }

    @Test
    void delete() {
        service.delete(MENU_ID);
        assertThrows(NotFoundException.class,()-> service.get(MENU_ID));
    }

    @Test
    void save() {
        Menu m = service.save(NEW_MENU);
        Menu z = service.get(m.id());
        z.setRating(40);
        Menu f = new Menu(z);
        assertThat(m).isEqualTo(service.get(m.id()));
    }

    @Test
    void getMenuWithIdMenuAndDate(){
        Menu m = service.getMenuWithIdDate(MENU_ID, LocalDate.of(2020, Month.JANUARY, 30));
        assertThat(m).isEqualTo(MENU);
    }
}