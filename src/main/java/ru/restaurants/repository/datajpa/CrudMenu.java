package ru.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenu extends JpaRepository<Menu, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete (@Param("id") int id);

    @Modifying
    @Query("FROM Menu m WHERE m.rest.id=:id_rest")
    List<Menu> getAllMenuOfRest(@Param("id_rest") int id_rest);

    @Modifying
    @Query("FROM Menu m WHERE m.dateMenu=:date ORDER BY m.menuRest")
    List<Menu> getAllDate(@Param("date") LocalDate date);

    @Query("FROM Menu m WHERE m.rest.id=:id AND m.dateMenu=:date")
    Menu getMenuWithIdRestAndDate (@Param("id") int id, @Param("date") LocalDate date);
}
