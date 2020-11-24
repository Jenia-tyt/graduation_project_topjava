package ru.restaurants.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.restaurants.model.User;
import ru.restaurants.repository.UserRepository;

import java.util.List;

@Repository
public class UserDateJpaRepository implements UserRepository {

    private final CrudUser crudUser;

    public UserDateJpaRepository(CrudUser crudUser) {
        this.crudUser = crudUser;
    }

    @Override
    public User get(int id) {
        return crudUser.findById(id).orElse(null);
    }


    @Override
    public User getByEmail(String email) {
        return crudUser.getByEmail(email);
    }

    @Override
    public boolean delete(int id) {
        return crudUser.delete(id) != 0;
    }

    @Override
    public User save(User user) {
        return crudUser.save(user);
    }
}
