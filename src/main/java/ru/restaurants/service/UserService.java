package ru.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurants.model.User;
import ru.restaurants.repository.datajpa.UserDateJpaRepository;

import java.util.List;

import static ru.restaurants.util.ValidationUtil.*;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDateJpaRepository repository;

    public UserService(UserDateJpaRepository repository) {
        this.repository = repository;
    }

    public User get (int id){
        LOG.info("Get user with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email){
        LOG.info("Get user with email {}", email);
        Assert.notNull(email, "Email doesn't be null");
        return checkNotFound(repository.getByEmail(email), email);
    }

    public void delete(int id){
        LOG.info("User deleted with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void upDate(User user){
        LOG.info("User update with");
        Assert.notNull(user, "User doesn't be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    public User create(User user){
        LOG.info("User {} saved with", user);
        Assert.notNull(user, "User doesn't be null");
        return repository.save(user);
    }
}
