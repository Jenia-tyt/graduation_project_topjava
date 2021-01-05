package ru.restaurants.to;

import ru.restaurants.HasId;

import java.io.Serializable;

public abstract class AbstractTo implements HasId, Serializable {
    protected Integer id;

    public AbstractTo() {
    }

    public AbstractTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }
}
