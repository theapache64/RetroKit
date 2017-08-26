package com.theah64.retrokit.activities;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseRecyclerViewActivity<D,A> extends BaseDynamicActivity<D,A> {
    protected abstract int getRecyclerViewID();
}
