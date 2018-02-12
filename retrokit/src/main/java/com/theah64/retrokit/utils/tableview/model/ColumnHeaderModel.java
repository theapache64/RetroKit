package com.theah64.retrokit.utils.tableview.model;

import com.theah64.retrokit.utils.tableview.base.GhostableColumn;

/**
 * Created by evrencoskun on 27.11.2017.
 */

public class ColumnHeaderModel implements GhostableColumn {

    private String mData;

    public ColumnHeaderModel(String mData) {
        this.mData = mData;
    }

    public String getData() {
        return mData;
    }

    @Override
    public String getColumnName() {
        return mData;
    }
}
