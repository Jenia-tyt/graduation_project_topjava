package ru.restaurants.model;


import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity{

    @ManyToOne
    @NotNull
    @JoinColumn (name = "id_rest", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant rest;

    @Column(name = "date_menu")
    private LocalDate dateMenu;

    @Column(name = "menu")
    @NotNull
    @NotBlank
    private String menuRest;

    @Column(name = "rating")
    private Integer rating;

    public Menu() {}

    public Menu( @NotNull Restaurant r, @NotBlank @NotNull LocalDate dateMenu, @NotNull @NotBlank String menuRest, Integer rating) {
        this(null, r, dateMenu, menuRest, rating);
    }

    public Menu (Menu m){
        this(m.getId(), m.rest, m.getDateMenu(), m.getMenuRest(), m.getRating());
    }

    public Menu(Integer id, Restaurant r, LocalDate dateMenu, String menuRest, Integer rating) {
        super(id);
        this.rest = r;
        this.dateMenu = dateMenu;
        this.menuRest = menuRest;
        this.rating = rating == null ? 0 : rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public LocalDate getDateMenu() {
        return dateMenu;
    }

    public void setDateMenu(LocalDate dateMenu) {
        this.dateMenu = dateMenu;
    }

    public String getMenuRest() {
        return menuRest;
    }

    public void setMenuRest(String menuRest) {
        this.menuRest = menuRest;
    }

    @Override
    public String toString() {
        return  "Name rest" + rest.getName()  +
                "Date menu: " + dateMenu + "\n" +
                "Menu: " + menuRest +
                "Id rest: " + rest.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))){
            return false;
        }
        return this.id != null
                && this.id.equals(((Menu) o).id)
                && this.dateMenu.equals(((Menu) o).dateMenu)
                && this.rest.equals(((Menu) o).rest);
    }
}
