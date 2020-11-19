package ru.restaurants.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity{

    @Column(name = "name_rest")
    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String nameRest;

//    @Column(name = "id_rest")
//    @NotNull
//    private Integer idRest;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "id_rest", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant rest;

    @Column(name = "date_menu")
    @NotBlank
    @NotNull
    private LocalDateTime dateTimeMenu;

    @Column(name = "menu")
    @NotNull
    @NotBlank
    private String menuRest;

    public Menu() {}

    public Menu(@NotNull @NotBlank @Size(min = 2, max = 30) String nameRest, @NotNull Restaurant r, @NotBlank @NotNull LocalDateTime dateTimeMenu, @NotNull @NotBlank String menuRest) {
        this(null, nameRest, r, dateTimeMenu, menuRest);
    }

    public Menu (Menu m){
        this(m.getId(), m.getNameRest(), m.rest, m.getDateTimeMenu(), m.getMenuRest());
    }

    public Menu(Integer id, String nameRest, Restaurant r, LocalDateTime dateTimeMenu, String menuRest) {
        super(id);
        this.nameRest = nameRest;
        this.rest = r;
        this.dateTimeMenu = dateTimeMenu;
        this.menuRest = menuRest;
    }

    public String getNameRest() {
        return nameRest;
    }

    public void setNameRest(String nameRest) {
        this.nameRest = nameRest;
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

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Name restaurant" + nameRest + "\n" +
                "date menu: " + dateTimeMenu + "\n" +
                "Menu: " + menuRest +
                "Id rest: " + rest.getName();
    }
}
