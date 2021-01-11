package ru.restaurants.repository;

import ru.restaurants.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote get(int id);

    boolean delete (int id);

    Vote save (Vote vote);

    List<Vote> allVote();

    List<Vote> allVoteByUser(int idUser);

}
