package ru.romanbrazhnikov.simplefitnessdiary.base.views;

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
 * Basic list view Activity implementing boilerplate for the ViewHolder pattern
 * based on RecyclerView.
 * <p>
 * Extend this class and override abstract methods to make your activity work.
 * Generics:
 * T - type of the item model, that is used in Adapter
 * V - successor of BaseViewHolder, that binds model and layout of an item
 * <p>
 * Methods:
 * int getRecyclerViewID()
 * - returns id of the RecyclerView widget used in the screen layout
 * <p>
 * int getItemLayoutID()
 * - returns id of an item layout used within recycler view list
 * <p>
 * V newViewHolder(View itemView)
 * - generates and returns NEW successor of the BaseViewHolder
 * <p>
 * int getScreenLayout()
 * - returns the screen layout ID to be set
 */

public abstract class BaseRecyclerViewActivity<T, V extends BaseViewHolder> extends BaseActivity {

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

    protected void refreshList(List<T> items){
        if (mBaseAdapter == null) {
            resetAdapter(items);
        } else {
            updateAdapter(items);
        }
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

    /**
     * Returns layout id for the screen
     */
    protected abstract int getScreenLayout();

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
