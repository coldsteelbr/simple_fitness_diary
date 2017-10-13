package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private ExerciseSelectedListener mExerciseSelectedListener = new ExerciseSelectedListener();
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

    private void initSpinner() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exerciseArray);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnExerciseTypes.setAdapter(mAdapter);
        spnExerciseTypes.setOnItemSelectedListener(mExerciseSelectedListener);
    }

    private View inflateMeasurementItem(String type, String value, String unit) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View measurementItem = inflater.inflate(R.layout.item_measurement, null);

        TextView tv_measurementType = measurementItem.findViewById(R.id.tv_measurementType);
        TextView tv_unit = measurementItem.findViewById(R.id.tv_unit);
        EditText et_value = measurementItem.findViewById(R.id.et_value);

        if (unit != null) {
            tv_measurementType.setText(type);
        }
        if (unit != null) {
            tv_unit.setText(unit);
        }
        if (value != null) {
            et_value.setText(value);
        }

        return measurementItem;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        spnExerciseTypes.setSelection(mAdapter.getPosition(mTrainingSet.getExerciseType()));
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

    class ExerciseSelectedListener implements Spinner.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            llResult.removeAllViews();
            for (String curKey :
                    mTrainingSet.getMeasurements().keySet()) {
                llResult.addView(inflateMeasurementItem(curKey, mTrainingSet.getMeasurements().get(curKey), null));
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
