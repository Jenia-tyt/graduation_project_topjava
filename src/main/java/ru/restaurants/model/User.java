package ru.restaurants.model;

import java.util.List;
import java.util.Set;

public class User extends AbstractBaseEntity{

    private String email;

    private String name;

    private String password;

    private Set<Role> role;

    private List<Vote> votes;

    private boolean voteToDay;

    public User() {
    }

    public User (User user){
        this(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getRole(), user.getVotes(),user.isVoteToDay());
    }

    public User(String email, String name, String password, Set<Role> role, List<Vote> votes, boolean voteToDay) {
        this(null, email, name, password, role, votes, voteToDay);
    }

    public User(Integer id, String email, String name, String password, Set<Role> role, List<Vote> votes, boolean voteToDay) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.votes = votes;
        this.voteToDay = voteToDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public boolean isVoteToDay() {
        return voteToDay;
    }

    public void setVoteToDay(boolean voteToDay) {
        this.voteToDay = voteToDay;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
