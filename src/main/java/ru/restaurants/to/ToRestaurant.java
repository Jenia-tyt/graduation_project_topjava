package ru.restaurants.to;
import ru.restaurants.model.Menu;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ToRestaurant extends AbstractTo{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name;

    private List<Menu> menu;

    private Integer rating;

    public ToRestaurant() {
    }

    public ToRestaurant(Integer id, String name, List<Menu> menu, Integer rating) {
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

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
