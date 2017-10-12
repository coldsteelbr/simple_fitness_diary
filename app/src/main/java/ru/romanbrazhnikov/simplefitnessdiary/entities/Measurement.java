package ru.romanbrazhnikov.simplefitnessdiary.entities;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by roman on 12.10.17.
 */
@Entity
public class Measurement {
    @Id
    long id;
    String value;
    String typeName; // todo Measurement type

    public Measurement() {
    }

    public Measurement(String value, String typeName) {
        this.value = value;
        this.typeName = typeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
