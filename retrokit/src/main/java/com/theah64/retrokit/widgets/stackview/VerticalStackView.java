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

    public VerticalStackView(Context context) {
        super(context);
    }

    public VerticalStackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(StackViewAdapter adapter) {
        setOrientation(VERTICAL);
        final List dataList = adapter.getDataList();
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (Object item : dataList) {
            final View row = layoutInflater.inflate(adapter.getLayoutRow(), this, false);
            adapter.bind(adapter.getViewHolder(row), item);
            addView(row);
        }
    }

}
