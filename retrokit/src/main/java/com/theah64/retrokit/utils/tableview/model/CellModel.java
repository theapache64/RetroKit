package com.theah64.retrokit.utils.tableview.model;

import com.evrencoskun.tableview.sort.ISortableModel;

/**
 * Created by evrencoskun on 27.11.2017.
 */

public class CellModel implements ISortableModel {

    private final String mId;
    private final Object mData;

    public CellModel(String pId, Object mData) {
        this.mId = pId;
        this.mData = mData;
    }

    public CellModel(Object mData) {
        this(mData != null ? mData.toString() : null, mData);
    }

    public Object getData() {
        return mData;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public Object getContent() {
        return mData;
    }

}
