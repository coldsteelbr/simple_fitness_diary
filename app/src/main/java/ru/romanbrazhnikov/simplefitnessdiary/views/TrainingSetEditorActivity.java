package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

/**
 * Created by roman on 09.10.17.
 */

public class TrainingSetEditorActivity extends BaseActivity {
    // CONSTANTS
    private static final String CUSTOM_EXTRA_TRAINING_SET_ID = "CUSTOM_EXTRA_TRAINING_SET_ID";
    private static final String CUSTOM_EXTRA_TRAINING_SESSION_ID = "CUSTOM_EXTRA_TRAINING_SESSION_ID";

    //FIELDS
    @Inject
    Box<TrainingSet> mTrainingSetBox;

    private TrainingSet mTrainingSet;
    private long mId;
    // WIDGETS
    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;

    // LISTENERS
    private SaveClickListener mSaveClickListener = new SaveClickListener();
    private long mSessionId;

    public static void showNewActivity(Context context, Long trainingSetID, Long sessionID) {
        Intent intent = new Intent(context, TrainingSetEditorActivity.class);
        intent.putExtra(CUSTOM_EXTRA_TRAINING_SET_ID, trainingSetID);
        intent.putExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, sessionID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_set_editor);

        ButterKnife.bind(this);

        mId = getIntent().getLongExtra(CUSTOM_EXTRA_TRAINING_SET_ID, 0);
        if (mId == 0) {
            mSessionId = getIntent().getLongExtra(CUSTOM_EXTRA_TRAINING_SESSION_ID, 0);
            mTrainingSet = TrainingSet.getDefaultSet();
            mTrainingSet.setSessionId(mSessionId);
        }

        fabSave.setOnClickListener(mSaveClickListener);
    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
    }

    class SaveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mTrainingSetBox.put(mTrainingSet);
            Toast.makeText(TrainingSetEditorActivity.this, "Save", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
