package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    String[] exerciseArray = {"pull-ups", "push-ups", "jogging", "bench press"};

    // CONSTANTS
    private static final String CUSTOM_EXTRA_TRAINING_SET_ID = "CUSTOM_EXTRA_TRAINING_SET_ID";
    private static final String CUSTOM_EXTRA_TRAINING_SESSION_ID = "CUSTOM_EXTRA_TRAINING_SESSION_ID";

    //FIELDS
    @Inject
    Box<TrainingSet> mTrainingSetBox;

    private TrainingSet mTrainingSet;
    private long mId;
    private ArrayAdapter<String> mAdapter;


    // WIDGETS
    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;
    @BindView(R.id.ll_result)
    ViewGroup llResult;
    @BindView(R.id.spn_exercise_types)
    Spinner spnExerciseTypes;

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
        } else {
            mTrainingSet = mTrainingSetBox.get(mId);
        }

        initSpinner();
        fabSave.setOnClickListener(mSaveClickListener);
    }

    private void initSpinner(){
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, exerciseArray);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnExerciseTypes.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        spnExerciseTypes.setSelection(mAdapter.getPosition(mTrainingSet.getExerciseType()));
        // TODO: update UI elements
        //etResult.setText(mTrainingSet.getMeasurements());
    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
    }

    class SaveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // TODO: save measurements from UI to MAP
            //mTrainingSet.setMeasurements(etResult.getText().toString());
            mTrainingSetBox.put(mTrainingSet);
            Toast.makeText(TrainingSetEditorActivity.this, "Save", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
