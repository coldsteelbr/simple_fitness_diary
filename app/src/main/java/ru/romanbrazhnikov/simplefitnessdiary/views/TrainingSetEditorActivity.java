package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.BaseActivity;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

/**
 * Created by roman on 09.10.17.
 */

public class TrainingSetEditorActivity extends BaseActivity {
    // CONSTANTS
    private static final String CUSTOM_EXTRA_TRAINING_SET = "CUSTOM_EXTRA_TRAINING_SET";

    //FIELDS
    private TrainingSet mTrainingSet;
    private Context mContext;

    // WIDGETS
    @BindView(R.id.b_save)
    Button bSave;

    // LISTENERS
    private SaveClickListener mSaveClickListener = new SaveClickListener();

    public static void showNewActivity(Context context, Long trainingSetID) {
        Intent intent = new Intent(context, TrainingSetEditorActivity.class);
        intent.putExtra(CUSTOM_EXTRA_TRAINING_SET, trainingSetID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_set_editor);

        ButterKnife.bind(this);

        bSave.setOnClickListener(mSaveClickListener);
    }

    @Override
    public void inject() {
        getAppComponent().inject(this);
    }

    class SaveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(TrainingSetEditorActivity.this, "Save", Toast.LENGTH_SHORT).show();
        }
    }
}
