package ru.restaurants.model;


import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity{

    @ManyToOne
    @NotNull
    @JoinColumn (name = "id_rest", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant rest;

    @Column(name = "date_menu")
    private LocalDateTime dateTimeMenu;

    @Column(name = "menu")
    @NotNull
    @NotBlank
    private String menuRest;

    public Menu() {}

    public Menu( @NotNull Restaurant r, @NotBlank @NotNull LocalDateTime dateTimeMenu, @NotNull @NotBlank String menuRest) {
        this(null, r, dateTimeMenu, menuRest);
    }

    public Menu (Menu m){
        this(m.getId(), m.rest, m.getDateTimeMenu(), m.getMenuRest());
    }

    public Menu(Integer id, Restaurant r, LocalDateTime dateTimeMenu, String menuRest) {
        super(id);
        this.rest = r;
        this.dateTimeMenu = dateTimeMenu;
        this.menuRest = menuRest;
    }

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public LocalDateTime getDateTimeMenu() {
        return dateTimeMenu;
    }

    public void setDateTimeMenu(LocalDateTime dateTimeMenu) {
        this.dateTimeMenu = dateTimeMenu;
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
                "Date menu: " + dateTimeMenu + "\n" +
                "Menu: " + menuRest +
                "Id rest: " + rest.getName();
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
                && this.dateTimeMenu.equals(((Menu) o).dateTimeMenu)
                && this.rest.equals(((Menu) o).rest);
    }
}
