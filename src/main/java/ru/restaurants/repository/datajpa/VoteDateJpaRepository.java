package ru.restaurants.repository.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.restaurants.model.Vote;
import ru.restaurants.repository.VoteRepository;

import java.util.List;

@Repository
public class VoteDateJpaRepository implements VoteRepository {
    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "id");

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
}
