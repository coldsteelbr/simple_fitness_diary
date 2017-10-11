package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;
import io.objectbox.query.Query;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseRecyclerViewActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSession;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

public class MainActivity extends BaseRecyclerViewActivity<TrainingSession, TrainingSessionViewHolder> {
    // FIELDS
    @Inject
    Box<TrainingSet> mTrainingSetBox;

    @Inject
    Box<TrainingSession> mTrainingSessionBox;

    // WIDGETS
    @BindView(R.id.fab_addTrainingSession)
    FloatingActionButton fab_addTrainingSet;


    // LISTENERS
    AddClickListener mAddClickListener = new AddClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initListeners();

        /*
        TrainingSession session1 = new TrainingSession(new Date(), "Session 1 (" + Math.random() + ")");
        TrainingSession session2 = new TrainingSession(new Date(), "Session 2 (" + Math.random() + ")");
        mTrainingSessionBox.put(session1);
        mTrainingSessionBox.put(session2);
        */

    }


    private void initListeners() {
        fab_addTrainingSet.setOnClickListener(mAddClickListener);
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
        // getting items and refreshing list
        Query<TrainingSession> queryAll
                = mTrainingSessionBox.query()
                .build();

        List<TrainingSession> filteredRecords = queryAll.find();

        refreshList(filteredRecords);
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.rv_trainingSessions;
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.item_training_session;
    }

    @Override
    protected TrainingSessionViewHolder newViewHolder(View itemView) {
        return new TrainingSessionViewHolder(itemView, this);
    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
    }

    class AddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TrainingSessionActivity.showNewActivity(MainActivity.this, null);
        }
    }
}
