package ru.restaurants.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import javax.persistence.*;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;


@MappedSuperclass
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class AbstractBaseEntity implements Persistable<Integer> {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator (name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "global_seq")

    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return this.id==null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))){
            return false;
        }
        return id != null && id.equals(((AbstractBaseEntity) o).id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + id;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    public int id(){
        Assert.notNull(id, "Entity must have id");
        return id;
    }
}
