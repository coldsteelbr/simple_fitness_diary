package ru.romanbrazhnikov.simplefitnessdiary.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.romanbrazhnikov.simplefitnessdiary.views.TrainingSessionListActivity;
import ru.romanbrazhnikov.simplefitnessdiary.views.TrainingSessionActivity;
import ru.romanbrazhnikov.simplefitnessdiary.views.TrainingSetEditorActivity;

/**
 * Created by roman on 07.10.17.
 */


@Singleton
@Component(
        modules = {
                AppModule.class,
                ObjectBoxModule.class
        })
public interface AppComponent {
    void inject(TrainingSessionListActivity activity);
    void inject(TrainingSetEditorActivity activity);
    void inject(TrainingSessionActivity activity);

}

