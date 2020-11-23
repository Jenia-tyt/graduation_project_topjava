package ru.restaurants.model;



import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity{

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 8, max = 30)
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("dateVote DESC")
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
