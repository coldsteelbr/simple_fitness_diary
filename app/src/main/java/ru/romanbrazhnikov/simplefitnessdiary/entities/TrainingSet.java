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
    Date date;
    String exerciseType;
    String measurement;
}
