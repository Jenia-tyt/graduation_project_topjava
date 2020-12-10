package ru.restaurants.repository;

import ru.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote get(int id);

    List<Vote> getAllByRest(int idRest);

    boolean delete (int id);

    Vote save (Vote vote);

    List<Vote> allVote();

//    Vote getVoteOfUserToDay (int idUser, LocalDate date);
}
