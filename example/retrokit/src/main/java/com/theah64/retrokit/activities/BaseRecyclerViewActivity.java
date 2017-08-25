package com.theah64.retrokit.activities;

import com.theah64.retrokit.retro.BaseAPIResponse;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseRecyclerViewActivity<A> extends BaseDynamicActivity<BaseAPIResponse<G>, A> {
    protected abstract int getRecyclerViewID();

    @Override
    public void onSuccess(BaseAPIResponse<G> response) {

    }
}
