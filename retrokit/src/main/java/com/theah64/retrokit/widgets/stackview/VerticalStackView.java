package com.theah64.retrokit.widgets.stackview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by theapache64 on 20/2/18.
 */

public class VerticalStackView extends LinearLayout {

    private StackViewAdapter adapter;
    private LayoutInflater layoutInflater;

    public VerticalStackView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.layoutInflater = LayoutInflater.from(getContext());
    }

    public VerticalStackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setAdapter(StackViewAdapter adapter) {

        this.adapter = adapter;

        setOrientation(VERTICAL);
        final List dataList = adapter.getDataList();
        for (Object item : dataList) {
            final View row = layoutInflater.inflate(adapter.getLayoutRow(), this, false);
            adapter.bind(adapter.getViewHolder(row), item);
            addView(row);
        }

    }

    public void addView(int i, Object item) {
        final View row = layoutInflater.inflate(adapter.getLayoutRow(), null, false);
        adapter.bind(adapter.getViewHolder(row), item);
        addView(row);
    }
}
