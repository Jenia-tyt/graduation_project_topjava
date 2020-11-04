package ru.restaurants.model;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "rest_unic_name_index"))
public class Restaurant extends AbstractBaseEntity{

    @Column(name = "name")
    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @Column(name = "menu")
    @NotBlank
    private String menu;

    @Column(name = "rating")
    private Integer rating;

    public Restaurant() {
    }

    public Restaurant(@NotBlank @NotNull @Size(min = 3, max = 30) String name, @NotBlank String menu, Integer rating) {
       this(null, name, menu, rating);
    }

    public Restaurant (Restaurant r){
        this(r.getId(), r.name, r.menu, r.rating);
    }

    public Restaurant (Integer id, String name, String menu, Integer rating){
        super(id);
        this.name = name;
        this.menu = menu;
        this.rating = rating;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ID" + getId() + "\n" +
                "Название ресторана: " + name +"\n" +
                "menu: " + menu + "\n" +
                "рейтинг: " + rating + "\n";
    }
}
