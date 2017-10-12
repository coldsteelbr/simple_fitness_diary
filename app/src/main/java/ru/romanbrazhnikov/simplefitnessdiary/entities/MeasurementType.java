package ru.romanbrazhnikov.simplefitnessdiary.entities;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by roman on 12.10.17.
 * Describes a measurement. The table contains a final list of possible measurementTypes in the system
 *
 * Name: distance, weight, reps, time etc
 * Unit: km, kg, times, h:m:s etc
 */

@Entity
public class MeasurementType {
    @Id
    long id;
    String name; // Distance, weight, reps, time
    String unit; // km, kg, times, h:m:s

    public MeasurementType() {
    }

    public MeasurementType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
