package ru.restaurants.repository.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.Menu;
import ru.restaurants.repository.DataTest;
import ru.restaurants.util.execption.NotFoundException;
import ru.restaurants.web.controller.MenuController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenuDataJpaReposiroryTest extends AbstractDataJpaTest{

    @Autowired
    MenuController controller;

    @Test
    void duplicateCreateMenu(){
        Menu m = DataTest.MENU;
        m.setId(null);
        assertThrows(DataAccessException.class, () -> controller.save(m));
    }

    @Test
    void deleteNotFounded(){
        assertThrows(NotFoundException.class, ()-> controller.delete(DataTest.NOT_ID_MENU));
    }

    @Test
    void getNotFounded(){
        assertThrows(NotFoundException.class, ()-> controller.get(DataTest.NOT_ID_MENU));
    }

    @Test
    void getAll() {
        List<Menu> allMenu = controller.getAll();
        List<Menu> z = new ArrayList<>(DataTest.ALL_MENU);
        z.sort(Comparator.comparing(m-> m.id()));
        assertThat(allMenu).isEqualTo(z);
    }

    @Test
    void getAllMenuOfRest() {
        List<Menu> allMenuOfRest = controller.getAllMenuOfRest(DataTest.REST_ID);
        assertThat(allMenuOfRest).isEqualTo(DataTest.MENU_OF_REST);
    }

    @Test
    void getAllDate() {
        List<Menu> allMenuOfDate = controller.getAllDate(DataTest.MENU_DATA);
        allMenuOfDate.sort(Comparator.comparing(m -> m.getRest().getName()));
        List<Menu> z = new ArrayList<>(DataTest.ALL_MENU_OF_DATE_MENU_DATE);
        z.sort(Comparator.comparing(m -> m.getRest().getName()));
        assertThat(z).isEqualTo(allMenuOfDate);
    }

    @Test
    void get() {
        assertThat(DataTest.MENU).isEqualTo(controller.get(DataTest.MENU_ID));
    }

    @Test
    void delete() {
        controller.delete(DataTest.MENU_ID);
        assertThrows(NotFoundException.class,()-> controller.get(DataTest.MENU_ID));
    }

    @Test
    void save() {
        Menu m = controller.save(DataTest.NEW_MENU);
        assertThat(m).isEqualTo(controller.get(m.id()));
    }
}