package com.theah64.retrokit.activities;

import android.support.v7.widget.RecyclerView;

import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseRecyclerViewActivity<M, D, A> extends BaseDynamicActivity<D, A> {

    protected abstract RecyclerView getRecyclerView();

    @Override
    protected void onSuccess(D body) {
        getRecyclerView().setAdapter(getNewAdapter(getData(body)));
    }

    public abstract List<M> getData(D data);

    public abstract BaseRecyclerViewAdapter getNewAdapter(List<M> data);

}
