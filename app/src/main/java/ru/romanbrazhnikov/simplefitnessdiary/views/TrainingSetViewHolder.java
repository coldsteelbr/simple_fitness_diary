package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.view.View;
import android.widget.TextView;

import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.viewholders.BaseViewHolder;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSet;

/**
 * Created by roman on 07.10.17.
 */

public class TrainingSetViewHolder extends BaseViewHolder<TrainingSet> {
    private TextView tvContent;

    public TrainingSetViewHolder(View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    @Override
    public void bindItem(TrainingSet set) {
        tvContent.setText(set.getExerciseType() + " " + set.getMeasurement());
    }

}
