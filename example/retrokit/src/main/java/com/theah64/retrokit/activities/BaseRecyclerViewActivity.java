package com.theah64.retrokit.activities;

import com.theah64.retrokit.retro.BaseAPIResponse;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseRecyclerViewActivity extends BaseDynamicActivity<BaseAPIResponse> {
    protected abstract int getRecyclerViewID();

    @Override
    public void onSuccess(BaseAPIResponse response) {

    }
}
