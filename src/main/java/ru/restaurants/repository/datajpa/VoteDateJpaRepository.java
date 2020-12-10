package ru.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Vote;
import ru.restaurants.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteDateJpaRepository implements VoteRepository {

    @Autowired
    private final CrudVote crudVote;

    public VoteDateJpaRepository(CrudVote crudVote) {
        this.crudVote = crudVote;
    }

    @Override
    public Vote get(int id) {
        return crudVote.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAllByRest(int idRest) {
        return crudVote.getAllByRest(idRest);
    }

    @Override
    public boolean delete(int id) {
        return crudVote.delete(id) != 0;
    }

    @Override
    public Vote save(Vote vote) {
        return crudVote.save(vote);
    }

    @Override
    public List<Vote> allVote() {
        return crudVote.findAll();
    }


    public Vote getVoteOfUserToDay(int idUser, LocalDate date) {
        return crudVote.getVoteOfUserToDay(idUser, date);
    }
}
