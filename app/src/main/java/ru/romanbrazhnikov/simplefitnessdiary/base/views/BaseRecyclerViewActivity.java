package ru.romanbrazhnikov.simplefitnessdiary.base.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.romanbrazhnikov.simplefitnessdiary.base.views.viewholders.BaseViewHolder;

/**
 * Created by roman on 07.10.17.
 */

public abstract class BaseRecyclerViewActivity<T, V extends BaseViewHolder> extends BaseActivity {

    private Context mContext;
    private RecyclerView rvTrainingList;
    private int mRecyclerViewID;
    private int mItemLayout;

    private BaseAdapter mBaseAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerViewID = getRecyclerViewID();
        mItemLayout = getItemLayoutID();

        setContentView(getScreenLayout());

        // Recycler view
        rvTrainingList = findViewById(mRecyclerViewID);
        rvTrainingList.setLayoutManager(new LinearLayoutManager(this));
    }

    protected abstract int getScreenLayout();

    protected BaseAdapter getAdapter() {
        return mBaseAdapter;
    }

    protected void resetAdapter(List<T> items) {
        mBaseAdapter = new BaseAdapter(items);
        rvTrainingList.setAdapter(mBaseAdapter);
    }

    protected void updateAdapter(List<T> items) {
        mBaseAdapter.updateData(items);
        mBaseAdapter.notifyDataSetChanged();
    }

    /**
     * Returns recycler view id: R.id.rv_BlaBla
     */
    protected abstract int getRecyclerViewID();

    /**
     * Returns item layout id: R.layout.item_bla_bla
     */
    protected abstract int getItemLayoutID();


    /**
     * Returns another view holder
     */
    protected abstract V newViewHolder(View itemView);


    class BaseAdapter extends RecyclerView.Adapter<V> {

        private List<T> mItemList = new ArrayList<>();

        public BaseAdapter(List<T> itemList) {
            updateData(itemList);
        }

        @Override
        public V onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View view = li.inflate(mItemLayout, parent, false);
            return newViewHolder(view);
        }

        @Override
        public void onBindViewHolder(V holder, int position) {
            T set = mItemList.get(position);
            holder.bindItem(set);
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }

        public void updateData(List<T> newList) {
            mItemList.clear();
            mItemList.addAll(newList);
            this.notifyDataSetChanged();
        }
    }
}
