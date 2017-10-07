package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.os.Bundle;
import android.view.View;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.objectbox.Box;
import io.objectbox.query.Query;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseRecyclerViewActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

public class MainActivity extends BaseRecyclerViewActivity<TrainingSet, TrainingSetViewHolder> {
    @Inject
    Box<TrainingSet> mTrainingSetBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        TrainingSet testSet = new TrainingSet();
        testSet.setDate(new Date());
        testSet.setExerciseType("Pull-ups");
        testSet.setMeasurement("15 times");
        mTrainingSetBox.put(testSet);

        TrainingSet testSet2 = new TrainingSet();
        testSet2.setDate(new Date());
        testSet2.setExerciseType("Push-ups");
        testSet2.setMeasurement("30 times");
        mTrainingSetBox.put(testSet2);
    }

    @Override
    protected int getScreenLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

        // LIST
        // getting items and setting adapter
        Query<TrainingSet> queryAll
                = mTrainingSetBox.query()
                .build();

        List<TrainingSet> filteredRecords = queryAll.find();
        if (getAdapter() == null) {
            resetAdapter(filteredRecords);
        } else {
            updateAdapter(filteredRecords);
        }

    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.rv_trainingSets;
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.item_training_set;
    }

    @Override
    protected TrainingSetViewHolder newViewHolder(View itemView) {
        return new TrainingSetViewHolder(itemView);
    }


    @Override
    public void inject() {
        getAppComponent().inject(this);
    }


}
