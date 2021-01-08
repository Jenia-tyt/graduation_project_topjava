package ru.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity{

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
    @JsonIgnore
    private List<Vote> votes;

    @Column(name = "vote_last")
    private LocalDate voteLast;

    public User() {
    }

    public User (User user){
        this(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getVotes(), user.getVoteLast(), user.getRole());
    }

    public User(String email, String name, String password,  List<Vote> votes, LocalDate voteLast, Role role, Role... roles) {
        this(null, email, name, password, votes, voteLast, EnumSet.of(role, roles));
    }

    public User(Integer id, String email, String name, String password, List<Vote> votes, LocalDate voteLast, Collection<Role> role) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.votes = votes;
        this.voteLast = voteLast;
        setRole(role);
    }

    public User(Integer id, String email, String name, String password, List<Vote> votes, LocalDate voteLast, Role role) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.votes = votes;
        this.voteLast = voteLast;
        setRole(role);
    }

    public void setRole(Role r) {
        this.role = new HashSet<>();
        this.role.add(r);
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

    public void setRole(Collection<Role> role) {
        this.role = CollectionUtils.isEmpty(role) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(role);
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public LocalDate getVoteLast() {
        return voteLast;
    }

    public void setVoteLast(LocalDate voteLast) {
        this.voteLast = voteLast;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (this.getClass() != Hibernate.getClass(o)){
            return false;
        }
        return this.id != null
                && this.id.equals(((User)o).id)
                && this.name.equals(((User)o).name)
                && this.email.equals(((User) o).email);
    }
    @Override
    public String toString() {
        return "Id user " + id +
                "\nUser name " + name;
    }
}
