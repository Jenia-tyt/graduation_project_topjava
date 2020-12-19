package ru.restaurants.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDate dateVote;

    @Column(name = "id_menu")
    @NotNull
    private Integer idMenu;

    public Vote() {
    }

    public Vote(Integer id, User user, LocalDate dateVote, int idRest) {
        super(id);
        this.user = user;
        this.dateVote = dateVote;
        this.idMenu = idRest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDate dateVoit) {
        this.dateVote = dateVoit;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idRest) {
        this.idMenu = idRest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        return this.id != null
                && this.id.equals(((Vote)o).id)
                && this.dateVote.equals(((Vote)o).dateVote)
                && this.idMenu.equals(((Vote) o).idMenu)
                && this.user.equals(((Vote) o).user);
    }

    @Override
    public String toString() {
        return "User with id " + user.id +
                "voted " + dateVote +
                "behind " + idMenu;
    }
}
