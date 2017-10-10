package ru.romanbrazhnikov.simplefitnessdiary.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ru.romanbrazhnikov.simplefitnessdiary.R;
import ru.romanbrazhnikov.simplefitnessdiary.base.views.viewholders.BaseViewHolder;
import ru.romanbrazhnikov.simplefitnessdiary.entities.TrainingSession;

/**
 * Created by roman on 10.10.17.
 */

public class TrainingSessionViewHolder extends BaseViewHolder<TrainingSession> {

    private TrainingSession mItem;
    private TextView tv_sessionContent;


    public TrainingSessionViewHolder(View itemView, Context context) {
        super(itemView);
        tv_sessionContent = itemView.findViewById(R.id.tv_sessionTitle);
    }

    @Override
    public void bindItem(TrainingSession item) {
        mItem = item;
        tv_sessionContent.setText(item.getDate().toString() + " " +item.getDescription());
    }

}
