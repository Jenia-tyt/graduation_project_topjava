package ru.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurants.model.Vote;
import ru.restaurants.repository.VoteRepository;
import ru.restaurants.repository.datajpa.VoteDateJpaRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private static final Logger LOG = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private final VoteDateJpaRepository voteRepository;

    public VoteService(VoteDateJpaRepository voteRepository) {
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

    public void delete(int id){
        LOG.info("Vote deleted with id {}", id);
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void upDate(Vote vote, Integer id){
        LOG.info("Vote id {} update ", vote.getId());
        Assert.notNull(vote, "Vote doesn't be null");
        Assert.notNull(id, "Id doesn't be null");
        checkNotFoundWithId(voteRepository.save(vote), id);
    }

    public Vote create(Vote vote){
        LOG.info("Vote {} saved", vote);
        Assert.notNull(vote, "Vote doesn't be null");
        return voteRepository.save(vote);
    }

    public Vote getVoteOfUserToDay(Integer idUser, LocalDate date){
        LOG.info("Vote get of user to day");
        Assert.notNull(date, "Date doesn't be null");
        Assert.notNull(idUser, "User's id doesn't be null");
        return voteRepository.getVoteOfUserToDay(idUser, date);
    }

    public List<Vote> getAllVoteByUser(Integer idUser){
        LOG.info("Get all vote be user with id {}", idUser);
        Assert.notNull(idUser, "User's id doesn't be null");
        return voteRepository.allVoteByUser(idUser);
    }
}
