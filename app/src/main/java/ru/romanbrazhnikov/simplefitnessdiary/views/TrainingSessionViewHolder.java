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

public class TrainingSessionViewHolder extends BaseViewHolder<TrainingSession>
implements View.OnClickListener{

    private Context mContext;
    private TrainingSession mItem;
    private TextView tv_sessionContent;


    public TrainingSessionViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        itemView.setOnClickListener(this);
        tv_sessionContent = itemView.findViewById(R.id.tv_sessionTitle);
    }

    @Override
    public void bindItem(TrainingSession item) {
        mItem = item;
        tv_sessionContent.setText(item.getDate().toString() + " " +item.getDescription());
    }

    @Override
    public void onClick(View view) {
        TrainingSessionActivity.showNewActivity(mContext, mItem.getId());
    }
}
