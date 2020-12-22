package ru.restaurants.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface CrudVote extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete (@Param("id") Integer id);

    @Query ("FROM Vote v WHERE v.user.id=:idUser AND v.dateVote=:date")
    Vote getVoteOfUserToDay(@Param("idUser") Integer idUser, @Param("date") LocalDate date);

    @Query("FROM Vote v WHERE v.user.id=:idUser ORDER BY v.id")
    List<Vote> getAllVoteByUser(@Param ("idUser")int isUser);
}
