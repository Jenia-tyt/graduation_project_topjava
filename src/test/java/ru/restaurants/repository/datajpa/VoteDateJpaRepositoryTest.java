package ru.restaurants.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.restaurants.model.Vote;
import ru.restaurants.service.VoteService;
import ru.restaurants.util.execption.NotFoundException;

import java.util.List;

import static ru.restaurants.repository.VoteDateTest.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VoteDateJpaRepositoryTest extends AbstractDataJpaTest{

    @Autowired
    VoteService service;

    @Test
    void create() {
        Vote v = service.create(NEW_VOTE);
        int id  = v.getId();
        assertThat(v).isEqualTo(service.get(id));
    }

    @Test
    void upDate() {
        Vote v = NEW_VOTE;
        v.setId(VOTE_ID);
        service.upDate(v);
        assertThat(v).isEqualTo(service.get(VOTE_ID));
    }

    @Test
    void getAllByRest() {
        List<Vote> listAllByRest = service.getAllByRest(3);
        assertThat(listAllByRest).isEqualTo(LIST_VOTE_ALL_ID_REST_3);
    }

    @Test
    void voteDuplicateCreate (){
        Vote v = VOTE;
        v.setId(VOTE_ID_66);
        assertThrows(DataAccessException.class, ()-> service.create(v));
    }

    @Test
    void deleteNotFound(){
        assertThrows(NotFoundException.class, ()->service.delete(VOTE_ID_66));
    }

    @Test
    void getNotFound (){
        assertThrows(NotFoundException.class, ()-> service.get(VOTE_ID_66));
    }

    @Test
    void get() {
        Vote v = service.get(VOTE_ID);
        assertThat(v).isEqualTo(VOTE);
    }

    @Test
    void delete() {
        service.delete(VOTE_ID);
        assertThrows(NotFoundException.class, ()-> service.get(VOTE_ID));
    }

    @Test
    void allVote() {
        List<Vote> allVote = service.getAll();
        assertThat(allVote).isEqualTo(LIST_VOTE);
    }
}