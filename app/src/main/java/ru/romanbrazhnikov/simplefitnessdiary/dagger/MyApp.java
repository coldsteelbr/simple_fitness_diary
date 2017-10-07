package ru.romanbrazhnikov.simplefitnessdiary.dagger;

import android.app.Application;

/**
 * Created by roman on 07.10.17.
 */

public class MyApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .objectBoxModule(new ObjectBoxModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
