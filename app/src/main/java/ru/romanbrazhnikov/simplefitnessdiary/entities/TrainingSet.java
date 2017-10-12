package ru.romanbrazhnikov.simplefitnessdiary.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

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
    // TODO: To-Many relation
    @Convert(converter = MeasurementsConverter.class, dbType = String.class)
    Map<String, String> measurements; // "type:value;type:value..."

    static class MeasurementsConverter implements PropertyConverter<Map<String, String>, String> {

        @Override
        public Map<String, String> convertToEntityProperty(String databaseValue) {
            Map<String, String> result = new HashMap<>();
            if (databaseValue != null) {
                String[] pairs = databaseValue.split(";");
                for (String pair :
                        pairs) {
                    if (pair != null) {
                        String[] parsedPair = pair.split(":");
                        result.put(parsedPair[0], parsedPair[1]);
                    }
                }
            }
            return result;
        }

        @Override
        public String convertToDatabaseValue(Map<String, String> entityProperty) {
            StringBuilder builder = new StringBuilder();
            for (String key :
                    entityProperty.keySet()) {
                builder.append(key).append(":").append(entityProperty.get(key)).append(";");
            }
            return builder.toString();
        }
    }

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

    public Map<String, String> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Map<String, String> measurements) {
        this.measurements = measurements;
    }

    public static TrainingSet getDefaultSet() {
        TrainingSet set = new TrainingSet();
        set.setDate(new Date());
        return set;
    }
}
