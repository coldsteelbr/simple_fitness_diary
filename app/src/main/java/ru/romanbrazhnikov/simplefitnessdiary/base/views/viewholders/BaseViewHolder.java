package ru.romanbrazhnikov.simplefitnessdiary.base.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by roman on 07.10.17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindItem(T item);
}

