package com.theah64.retrokit.activities;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.theah64.retrokit.R;
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseRecyclerViewActivity<MODEL, RESPONSE_DATA, APIINTERFACE> extends BaseDynamicActivity<RESPONSE_DATA, APIINTERFACE> {

    private List<MODEL> fullDataList = new ArrayList<>();
    private BaseRecyclerViewAdapter<?, MODEL> adapter;

    protected abstract RecyclerView getRecyclerView();

    public abstract List<MODEL> getData(RESPONSE_DATA data);

    public abstract BaseRecyclerViewAdapter getNewAdapter(List<MODEL> data);

    @Override
    protected void onSuccess(RESPONSE_DATA response, boolean isClearList) {
        List<MODEL> dataList = getData(response);
        if (isClearList) {
            setFullDataList(dataList);
        } else {
            getFullDataList().addAll(dataList);
        }
        if (isClearList) {
            adapter = getNewAdapter(dataList);
            getRecyclerView().setAdapter(adapter);
        } else {
            adapter.getData().addAll(dataList);
            adapter.notifyDataSetChanged();
        }
    }

    public void setFullDataList(List<MODEL> fullDataList) {
        this.fullDataList.clear();
        this.fullDataList.addAll(fullDataList);
    }

    public List<MODEL> getFullDataList() {
        return fullDataList;
    }

    public BaseRecyclerViewAdapter<?, MODEL> getAdapter() {
        return adapter;
    }

    protected void onItemAdded(MODEL data) {
        Toast.makeText(this, R.string.Added, Toast.LENGTH_SHORT).show();
        getAdapter().getData().add(0, data);
        getAdapter().notifyItemInserted(0);
        getRecyclerView().scrollToPosition(0);
    }

    protected void onItemUpdated(MODEL old, MODEL newData) {
        Toast.makeText(this, R.string.Updated, Toast.LENGTH_SHORT).show();
        final int index = getAdapter().getData().indexOf(old);
        getAdapter().getData().remove(index);
        getAdapter().getData().add(index, newData);
        getAdapter().notifyItemChanged(index);
    }


    protected void onItemRemoved(MODEL removedItem) {
        Toast.makeText(this, R.string.Removed, Toast.LENGTH_SHORT).show();
        final int index = getAdapter().getData().indexOf(removedItem);
        getAdapter().getData().remove(index);
        getAdapter().notifyItemRemoved(index);
    }
}
