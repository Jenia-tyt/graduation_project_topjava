package ru.restaurants.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity{

    @Column(name = "name_Rest")
    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String nameRest;

    @Column(name = "id_rest")
    @NotNull
    private Integer idRest;

    @Column(name = "date_Menu")
    @NotBlank
    @NotNull
    private LocalDateTime dateTimeMenu;

    @Column(name = "menu")
    @NotNull
    @NotBlank
    private String menuRest;

    public Menu() {}

    public Menu(@NotNull @NotBlank @Size(min = 2, max = 30) String nameRest, @NotNull Integer idRest, @NotBlank @NotNull LocalDateTime dateTimeMenu, @NotNull @NotBlank String menuRest) {
        this(null, nameRest, idRest, dateTimeMenu, menuRest);
    }

    public Menu (Menu m){
        this(m.getId(), m.getNameRest(), m.idRest, m.getDateTimeMenu(), m.getMenuRest());
    }

    public Menu(Integer id, String nameRest, Integer idRest, LocalDateTime dateTimeMenu, String menuRest) {
        super(id);
        this.nameRest = nameRest;
        this.idRest = idRest;
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

    public Integer getIdRest() {
        return idRest;
    }

    public void setIdRest(Integer id_Rest) {
        this.idRest = id_Rest;
    }

    @Override
    public String toString() {
        return "Name restaurant" + nameRest + "\n" +
                "date menu: " + dateTimeMenu + "\n" +
                "Menu: " + menuRest +
                "Id rest: " + idRest;
    }
}
