package ru.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Menu;
import ru.restaurants.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurant extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete (@Param("id") int id);

    @Modifying
    @Query ("FROM Menu m WHERE m.rest.id=:id")
    List<Menu> getAllMenuForRest(@Param("id") int id);
}
