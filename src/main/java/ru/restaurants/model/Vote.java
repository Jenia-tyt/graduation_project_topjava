package ru.restaurants.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDateTime dateVote;

    @Column(name = "id_rest")
    @NotNull
    private Integer idRest;

    public Vote() {
    }

//    public Vote(User user, LocalDateTime dateVote, int idRest){
//        this(null, user, dateVote, idRest);
//    }

    public Vote(Integer id, User user, LocalDateTime dateVote, int idRest) {
        super(id);
        this.user = user;
        this.dateVote = dateVote;
        this.idRest = idRest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDateTime dateVoit) {
        this.dateVote = dateVoit;
    }

    public int getIdRest() {
        return idRest;
    }

    public void setIdRest(int idRest) {
        this.idRest = idRest;
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
                && this.id.equals(((Vote)o).id)
                && this.dateVote.equals(((Vote)o).dateVote)
                && this.idRest.equals(((Vote) o).idRest)
                && this.user.equals(((Vote) o).user);
    }

    @Override
    public String toString() {
        return "User with id " + user.id +
                "voted " + dateVote +
                "behind " + idRest;
    }
}
