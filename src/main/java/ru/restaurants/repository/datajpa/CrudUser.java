package ru.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import ru.restaurants.model.User;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudUser extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query ("DELETE FROM User u WHERE u.id=:id")
    int delete (@Param("id") Integer id);

    User getByEmail(String email);
}
