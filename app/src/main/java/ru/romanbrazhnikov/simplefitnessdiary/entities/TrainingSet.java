package ru.romanbrazhnikov.simplefitnessdiary.entities;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by roman on 04.10.17.
 */

@Entity
public class TrainingSet {
    @Id
    long id;
    long sessionId;
    Date date;
    String exerciseType;
    String measurement;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public static TrainingSet getDefaultSet() {
        TrainingSet set = new TrainingSet();
        set.setDate(new Date());
        set.setExerciseType("Default exercise");
        set.setMeasurement("0 times");
        return set;
    }
}
