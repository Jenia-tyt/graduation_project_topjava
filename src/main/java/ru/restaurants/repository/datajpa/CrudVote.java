package ru.restaurants.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurants.model.Vote;

import java.util.List;

public interface CrudVote extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete (@Param("id") Integer id);

    @Modifying
    @Query("FROM Vote v WHERE v.idRest=:idRest")
    List<Vote> getAllByRest(@Param("idRest") int idRest);
}
