package ru.restaurants;

import ru.restaurants.model.User;
import ru.restaurants.to.ToUser;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private ToUser toUser;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRole());
        this.toUser = convertUserInToUser(user);
    }

    public int getId() {
        return toUser.getId();
    }

    public void update(ToUser newTo) {
        toUser = newTo;
    }

    public ToUser getUserTo() {
        return toUser;
    }

    @Override
    public String toString() {
        return toUser.toString();
    }

    private ToUser convertUserInToUser (User user){
        return new ToUser(user.getId(), user.getName(), user.getVoteLast(), user.getEmail(), user.getPassword(), String.valueOf(user.getRole()));
    }
}
