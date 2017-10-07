package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.objectbox.Box;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseActivity;
import ru.romanbrazhnikov.simplefitnessdiary.dagger.MyApp;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

public class MainActivity extends BaseActivity {
    @Inject
    Box<TrainingSet> mTrainingSetBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
    }
}
