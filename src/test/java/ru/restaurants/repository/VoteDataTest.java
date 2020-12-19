package ru.restaurants.repository;

import ru.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.restaurants.repository.UserDataTest.USER;
import static ru.restaurants.repository.UserDataTest.*;

public class VoteDataTest {
    public static final Integer VOTE_ID = 18;
    public static final Vote VOTE = new Vote(VOTE_ID, USER, LocalDate.of(2020,01, 30), 11);
    public static final List<Vote> LIST_VOTE_ALL_ID_REST_3 = Arrays.asList(VOTE);

    private static final Vote VOTE_2 = new Vote(19, USER_ID_17, LocalDate.of(2020,01, 30), 12);
    private static final Vote VOTE_3 = new Vote(20, USER, LocalDate.now(), 14);
    public static final List<Vote> LIST_VOTE = Arrays.asList(VOTE, VOTE_2, VOTE_3);

    public static final Integer VOTE_ID_66 = 66;

    public static final Vote NEW_VOTE = new Vote(null, USER, LocalDate.of(2044,01, 30), 11);

    public static final Vote VOTE_TO_DAY = new Vote(20, USER, LocalDate.now(), 14);


}
