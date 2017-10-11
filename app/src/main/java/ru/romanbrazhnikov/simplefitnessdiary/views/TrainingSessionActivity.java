package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.query.Query;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseRecyclerViewActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSession;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet_;

/**
 * Created by roman on 10.10.17.
 */

public class TrainingSessionActivity extends BaseRecyclerViewActivity<TrainingSet, TrainingSetViewHolder> {
    // CONSTANTS
    private static final String CUSTOM_EXTRA_TRAINING_SESSION_ID = "CUSTOM_EXTRA_TRAINING_SESSION_ID";

    // FIELDS
    @Inject
    Box<TrainingSession> mTrainingSessionBox;

    @Inject
    Box<TrainingSet> mTrainingSetBox;

    private long mSessionId;

    public static void showNewActivity(Context context, Long trainingSessionID) {
        Intent intent = new Intent(context, TrainingSessionActivity.class);
        intent.putExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, trainingSessionID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionId = getIntent().getLongExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, 0);

        /*
        TrainingSession testSession = new TrainingSession();
        testSession.setDate(new Date());
        testSession.setDescription("Test (" + Math.random() + ")");
        mTrainingSessionBox.put(testSession);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        // getting items and refreshing list
        Query<TrainingSet> querySetsBySessionId
                = mTrainingSetBox.query().equal(TrainingSet_.sessionId, mSessionId)
                .build();

        List<TrainingSet> filteredRecords = querySetsBySessionId.find();

        refreshList(filteredRecords);
    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
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
        return new TrainingSetViewHolder(itemView, this);
    }

    @Override
    protected int getScreenLayout() {
        return R.layout.activity_training_session;
    }
}
