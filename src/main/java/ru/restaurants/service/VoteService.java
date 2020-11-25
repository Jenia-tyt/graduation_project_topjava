package ru.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurants.model.Vote;
import ru.restaurants.repository.VoteRepository;

import java.util.List;

import static ru.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private static final Logger LOG = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote get (int id){
        LOG.info("Get vote with id {}", id);
        return checkNotFoundWithId(voteRepository.get(id), id);
    }

    public List<Vote> getAll (){
        LOG.info("Get all votes");
        return voteRepository.allVote();
    }

    public List<Vote> getAllByRest(int idRest){
        LOG.info("Get all votes with id rest. {}", idRest);
        Assert.notNull(idRest, "Id rest. doesn't be null");
        return checkNotFoundWithId(voteRepository.getAllByRest(idRest), idRest);
    }

    public void delete(int id){
        LOG.info("Vote deleted with id {}", id);
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void upDate(Vote vote){
        LOG.info("Vote id {} update ", vote.getId());
        Assert.notNull(vote, "Vote doesn't be null");
        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public Vote create(Vote vote){
        LOG.info("Vote {} saved", vote);
        Assert.notNull(vote, "Vote doesn't be null");
        return voteRepository.save(vote);
    }
}
