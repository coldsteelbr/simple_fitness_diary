package ru.romanbrazhnikov.simplefitnessdiary.entities;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by roman on 04.10.17.
 */

@Entity
public class ExerciseType {
    @Id
    long id;
    String name;
}
