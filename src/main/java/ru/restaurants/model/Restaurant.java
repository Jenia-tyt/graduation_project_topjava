package ru.restaurants.model;



import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "rest_unic_name_index"))
public class Restaurant extends AbstractBaseEntity{

    @Column(name = "name")
    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rest")
    @OrderBy("dateTimeMenu DESC")
    private List <Menu> menu;

    @Column(name = "rating")
    private Integer rating;

    public Restaurant() {
    }

    public Restaurant(@NotBlank @NotNull @Size(min = 3, max = 30) String name, Integer rating) {
       this(null, name, rating);
    }

    public Restaurant (Restaurant r){
        this(r.getId(), r.name, r.rating);
    }

    public Restaurant (Integer id, String name, Integer rating){
        super(id);
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = new ArrayList<>(menu);
    }

    public void addMenuInList(Menu m){
        if (this.getMenu() != null) {
            menu.add(m);
        } else {
            List<Menu> menus = new ArrayList<>();
            menus.add(m);
            setMenu(menus);
        }
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
                "рейтинг: " + rating + "\n";
    }

    private String menuOfDate (){
        Menu z = menu.stream()
                .filter(m -> m.getDateTimeMenu().toLocalDate().equals(LocalDate.now()))
                .findFirst()
                .orElse(null);
        return z != null ? z.getMenuRest() : null;
    }
}