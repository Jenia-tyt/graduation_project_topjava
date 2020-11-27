package ru.restaurants.repository;

import ru.restaurants.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.restaurants.repository.UserDataTest.USER;
import static ru.restaurants.repository.UserDataTest.USER_ID_17;

public class VoteDataTest {
    public static final Integer VOTE_ID = 18;
    public static final Vote VOTE = new Vote(VOTE_ID, USER, LocalDateTime.of(2020,01, 30, 13,00, 00), 3);
    public static final List<Vote> LIST_VOTE_ALL_ID_REST_3 = Arrays.asList(VOTE);

    private static final Vote VOTE_2 = new Vote(19, USER_ID_17, LocalDateTime.of(2020,01, 30, 10,00, 00), 5);
    public static final List<Vote> LIST_VOTE = Arrays.asList(VOTE, VOTE_2);

    public static final Integer VOTE_ID_66 = 66;

    public static final Vote NEW_VOTE = new Vote(null, USER, LocalDateTime.of(2044,01, 30, 13,00, 00), 3);


}
