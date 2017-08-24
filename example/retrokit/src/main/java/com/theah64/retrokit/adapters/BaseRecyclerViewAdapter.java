package com.theah64.retrokit.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shifar on 15/9/16.
 */
    public abstract class BaseRecyclerViewAdapter<V extends BaseRecyclerViewHolder, M> extends RecyclerView.Adapter<V> {


    protected final Callback<M> callback;
    private List<M> data;
    private LayoutInflater inflater;


    public BaseRecyclerViewAdapter(final List<M> data, @Nullable Callback<M> callback) {
        this.data = data;
        this.callback = callback;
    }


    public abstract void onBindViewHolder(V holder, int position, M item);

    @Override
    public void onBindViewHolder(V holder, int position) {
        final M item = getData().get(position);
        onBindViewHolder(holder, position, item);
    }

    public final List<M> getData() {
        return this.data;
    }

    public final void setData(List<M> data) {
        this.data = data;
    }

    public Callback<M> getCallback() {
        return callback;
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        final View row = inflater.inflate(getRowLayoutID(), parent, false);
        return getNewRow(row);
    }

    @LayoutRes
    protected abstract int getRowLayoutID();

    protected abstract V getNewRow(View row);

    public interface Callback<M> {
        void onItemClick(M m, int position);
    }

}



