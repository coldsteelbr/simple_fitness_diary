package ru.romanbrazhnikov.simplefitnessdiary.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.romanbrazhnikov.simplefitnessdiary.views.MainActivity;

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
    void inject(MainActivity activity);

}

