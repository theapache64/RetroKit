package com.theah64.retrokit.widgets.stackview;

import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by theapache64 on 20/2/18.
 */

public class StackViewHolder {

    private final View row;

    public StackViewHolder(View row) {
        ButterKnife.bind(this, row);
        this.row = row;
    }

    public int getLayoutPosition() {
        final ViewGroup vg = (ViewGroup) row.getParent();
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i).equals(row)) {
                return i;
            }
        }
        return -1;
    }
}
