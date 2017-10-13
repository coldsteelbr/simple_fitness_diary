package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import io.objectbox.query.Query;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseRecyclerViewActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSession;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet_;
import ru.romanbrazhnikov.simplefitnessdiary.views.viewholders.TrainingSetViewHolder;

/**
 * Created by roman on 10.10.17.
 */
// TODO: save session ID
public class TrainingSessionActivity extends BaseRecyclerViewActivity<TrainingSet, TrainingSetViewHolder> {
    // CONSTANTS
    private static final String CUSTOM_EXTRA_TRAINING_SESSION_ID = "CUSTOM_EXTRA_TRAINING_SESSION_ID";

    // FIELDS
    @Inject
    Box<TrainingSession> mTrainingSessionBox;

    @Inject
    Box<TrainingSet> mTrainingSetBox;

    private long mSessionId;

    // WIDGETS
    @BindView(R.id.fab_addTrainingSet)
    FloatingActionButton fab_addSet;

    // LISTENERS
    private AddSetClickListener mAddSetClickListener = new AddSetClickListener();

    public static void showNewActivity(Context context, Long trainingSessionID) {
        Intent intent = new Intent(context, TrainingSessionActivity.class);
        intent.putExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, trainingSessionID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initListeners();

        mSessionId = getIntent().getLongExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, 0);
        if (mSessionId == 0) {
            TrainingSession session = new TrainingSession(new Date(), "New session");
            mSessionId = mTrainingSessionBox.put(session);
        }
    }

    private void initListeners() {
        fab_addSet.setOnClickListener(mAddSetClickListener);
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

    class AddSetClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TrainingSetEditorActivity.showNewActivity(
                    TrainingSessionActivity.this, 0L, mSessionId);
        }
    }
}
