package ru.romanbrazhnikov.simplefitnessdiary.entities;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by roman on 10.10.17.
 */

@Entity
public class TrainingSession {
    @Id
    long id;
    Date date;
    String description;

    public TrainingSession() {
    }

    public TrainingSession(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
