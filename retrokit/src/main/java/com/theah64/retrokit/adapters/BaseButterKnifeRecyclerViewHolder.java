package com.theah64.retrokit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;


/**
 * Created by theapache64 on 9/2/17.
 */

public class BaseButterKnifeRecyclerViewHolder<M> extends RecyclerView.ViewHolder {

    public BaseButterKnifeRecyclerViewHolder(View row, final BaseRecyclerViewAdapter adapter) {
        super(row);
        ButterKnife.bind(this, row);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (adapter.getCallback() != null) {
                    //noinspection unchecked
                    adapter.getCallback().onItemClick(adapter.getData().get(getLayoutPosition()), getLayoutPosition());
                }
            }
        });
    }
}
