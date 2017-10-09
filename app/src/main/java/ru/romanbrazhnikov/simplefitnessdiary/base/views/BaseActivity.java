package ru.romanbrazhnikov.simplefitnessdiary.base.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.romanbrazhnikov.simplefitnessdiary.dagger.AppComponent;
import ru.romanbrazhnikov.simplefitnessdiary.dagger.MyApp;

/**
 * Created by roman on 07.10.17.
 * Basic Activity. Includes dagger injection mechanism
 */

public abstract class BaseActivity extends AppCompatActivity {
    private AppComponent mAppComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppComponent = ((MyApp) getApplication()).getAppComponent();
        inject();
    }

    protected AppComponent getAppComponent(){
        return mAppComponent;
    }

    public abstract void inject();
}
