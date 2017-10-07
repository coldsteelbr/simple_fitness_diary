package ru.romanbrazhnikov.simplefitnessdiary.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import ru.romanbrazhnikov.simplefitnessdiary.entities.MyObjectBox;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

/**
 * Created by roman on 07.10.17.
 */

@Module
public class ObjectBoxModule {
    private BoxStore mStore;

    public ObjectBoxModule(MyApp application) {
        mStore = MyObjectBox.builder().androidContext(application).build();
    }

    @Provides
    @Singleton
    BoxStore provideBoxStore() {
        return mStore;
    }

    @Provides
    Box<TrainingSet> provideBoxForTrainingSet(){
        return mStore.boxFor(TrainingSet.class);
    }
}
