package ru.romanbrazhnikov.simplefitnessdiary.views.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.viewholders.BaseViewHolder;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;
import ru.romanbrazhnikov.simplefitnessdiary.views.TrainingSetEditorActivity;

/**
 * Created by roman on 07.10.17.
 */

public class TrainingSetViewHolder extends BaseViewHolder<TrainingSet>
        implements View.OnClickListener {
    private TextView tvContent;
    private long mTrainingSetID;
    private Context mContext;

    public TrainingSetViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        tvContent = itemView.findViewById(R.id.tv_content);
        itemView.setOnClickListener(this);
    }

    @Override
    public void bindItem(TrainingSet set) {
        mTrainingSetID = set.getId();
        tvContent.setText(set.getExerciseType() + " " + set.getMeasurements());
    }

    @Override
    public void onClick(View view) {
        TrainingSetEditorActivity.showNewActivity(mContext, mTrainingSetID, null);
    }
}
