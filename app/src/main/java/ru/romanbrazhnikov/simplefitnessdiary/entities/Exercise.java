package ru.romanbrazhnikov.simplefitnessdiary.entities;

import java.util.Arrays;
import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

/**
 * Created by roman on 12.10.17.
 * Name = push-ups, pull-ups, sit-ups, bench-press, jogging, skating etc
 * Measurements = list of: distance, time, reps, weight etc
 */
@Entity
public class Exercise {
    @Id
    long id;
    String name;
    @Convert(converter = MeasurementConverter.class, dbType = String.class)
    List<String> measurementTypes; // ;-split string list, i.e. "distance;time"

    /**
     * Converts between String "sub;sub;..." and List&lt;String&gt;
     */
    static class MeasurementConverter implements PropertyConverter<List<String>, String> {

        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            return Arrays.asList(databaseValue.split(";"));
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            StringBuilder builder = new StringBuilder();
            for (String item :
                    entityProperty) {
                builder.append(item);
            }
            return builder.toString();
        }
    }

    public Exercise() {
    }

    public Exercise(String name, List<String> measurementTypes) {
        this.name = name;
        this.measurementTypes = measurementTypes;
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

    public List<String> getMeasurementTypes() {
        return measurementTypes;
    }

    public void setMeasurementTypes(List<String> measurementTypes) {
        this.measurementTypes = measurementTypes;
    }
}
