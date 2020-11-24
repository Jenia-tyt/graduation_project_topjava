package ru.restaurants.repository;

import ru.restaurants.model.User;

import java.util.List;

public interface UserRepository {
    User get(int id);

    User getByEmail(String email);

    boolean delete (int id);

    User save (User user);
}
