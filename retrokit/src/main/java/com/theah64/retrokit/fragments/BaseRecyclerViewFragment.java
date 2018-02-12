package com.theah64.retrokit.fragments;

import android.support.v7.widget.RecyclerView;

import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by theapache64 on 15/11/17.
 */

public abstract class BaseRecyclerViewFragment<MODEL, RESPONSE_DATA, APIINTERFACE> extends BaseDynamicFragment<RESPONSE_DATA, APIINTERFACE> {

    protected abstract RecyclerView getRecyclerView();

    public abstract List<MODEL> getData(RESPONSE_DATA data);

    public abstract BaseRecyclerViewAdapter getNewAdapter(List<MODEL> data);

    @Override
    protected void onSuccess(RESPONSE_DATA data) {
        getRecyclerView().setAdapter(getNewAdapter(getData(data)));
    }
}