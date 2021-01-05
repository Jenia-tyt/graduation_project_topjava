package ru.restaurants.to;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ToMenu extends AbstractTo{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int id_rest;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMenu;
    private String menuRest;
    private int rating;

    public ToMenu(Integer id, int id_rest, LocalDate dateMenu, String menuRest, int rating) {
        this.id = id;
        this.id_rest = id_rest;
        this.dateMenu = dateMenu;
        this.menuRest = menuRest;
        this.rating = rating;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId_rest() {
        return id_rest;
    }

    public void setId_rest(int id_rest) {
        this.id_rest = id_rest;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
