package ru.restaurants.model;

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
    @NotEmpty
    private int idRest;

    public Vote() {
    }

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
}
