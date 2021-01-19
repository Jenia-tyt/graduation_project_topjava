package ru.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import ru.restaurants.AuthorizedUser;
import ru.restaurants.model.User;
import ru.restaurants.repository.datajpa.UserDateJpaRepository;

import java.time.LocalDate;
import java.util.List;


import static ru.restaurants.util.CheckedAdmin.checkedAdmin;
import static ru.restaurants.util.ValidationUtil.*;

@Service("UserService")
public class UserService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserDateJpaRepository repository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public UserService(UserDateJpaRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get (int id){
        LOG.info("Get user with id {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<User> getAll (){
        LOG.info("Get all users");
        return repository.getAll();
    }

    public User getByEmail(String email){
        LOG.info("Get user with email {}", email);
        Assert.notNull(email, "Email doesn't be null");
        String emailToLowerCase = email.toLowerCase();
        return checkNotFound(repository.getByEmail(emailToLowerCase), email);
    }

    public void delete(int id){
        LOG.info("User deleted with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, Integer id){
        LOG.info("User update with");
        Assert.notNull(user, "User doesn't be null");
        Assert.notNull(id, "Id doesn't be null");
        checkNotFoundWithId(repository.save(prepareToSave(user)), id);
    }

    public User create(User user){
        LOG.info("User {} saved with", user);
        Assert.notNull(user, "User doesn't be null");
        return repository.save(prepareToSave(user));
    }

    public void updateLastVote(User user, LocalDate date){
        LOG.info("User's {} last vote update", user);
        Assert.notNull(user, "User does,'t be null");
        Assert.notNull(date, "Date does,'t be null");
        user.setVoteLast(date);
        repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null){
            throw new UsernameNotFoundException("User with " + email + " not founded");
        } else {
            return new AuthorizedUser(user);
        }
    }

    private User prepareToSave (User user){
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
