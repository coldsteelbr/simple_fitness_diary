package ru.romanbrazhnikov.simplefitnessdiary.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roman on 07.10.17.
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }
}
