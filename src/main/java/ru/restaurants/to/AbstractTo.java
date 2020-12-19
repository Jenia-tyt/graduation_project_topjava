package ru.restaurants.to;

import ru.restaurants.HasId;

public abstract class AbstractTo implements HasId {
    protected Integer id;

    public AbstractTo() {
    }

    public AbstractTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }
}
