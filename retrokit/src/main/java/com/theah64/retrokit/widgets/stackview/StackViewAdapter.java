package com.theah64.retrokit.widgets.stackview;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

/**
 * Created by theapache64 on 20/2/18.
 */

public abstract class StackViewAdapter<MODEL, VH extends BaseViewHolder> {

    private final List<MODEL> dataList;

    protected StackViewAdapter(List<MODEL> dataList) {
        this.dataList = dataList;
    }


    public List<MODEL> getDataList() {
        return dataList;
    }

    @LayoutRes
    public abstract int getLayoutRow();

    public abstract void bind(VH row, MODEL item);

    public abstract VH getViewHolder(View row);
}
