package ru.romanbrazhnikov.simplefitnessdiary.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.romanbrazhnikov.simplefitnessdiary.entities.Exercise;

/**
 * Created by roman on 12.10.17.
 */
// TODO: rid of hardcoded data
public class ExerciseTypeFactory {
    private static List<Exercise> sExerciseList = null;

    public static List<Exercise> getExersiceTypes() {
        if (sExerciseList == null) {
            sExerciseList = new ArrayList<>();
            sExerciseList.add(new Exercise("pull-ups", Arrays.asList(new String[]{"reps"})));
            sExerciseList.add(new Exercise("push-ups", Arrays.asList(new String[]{"reps"})));
            sExerciseList.add(new Exercise("bench-press", Arrays.asList(new String[]{"reps", "weight"})));
            sExerciseList.add(new Exercise("jogging", Arrays.asList(new String[]{"distance", "time"})));
        }
        return sExerciseList;
    }
}
