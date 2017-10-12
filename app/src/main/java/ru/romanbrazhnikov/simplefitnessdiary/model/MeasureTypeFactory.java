package ru.romanbrazhnikov.simplefitnessdiary.model;

import ru.romanbrazhnikov.simplefitnessdiary.entities.MeasurementType;

/**
 * Created by roman on 12.10.17.
 */

public class MeasureTypeFactory {
    public static MeasurementType getDistanceType() {
        return new MeasurementType("distance", "km");
    }

    public static MeasurementType getRepsType() {
        return new MeasurementType("reps", "times");
    }

    public static MeasurementType getTimeType() {
        return new MeasurementType("time", "h:m:s");
    }

    public static MeasurementType getWeightType() {
        return new MeasurementType("weight", "kgs");
    }
}
