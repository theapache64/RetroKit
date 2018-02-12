package com.theah64.retrokit.fragments;

import com.theah64.retrokit.utils.ProgressManager;

/**
 * Created by theapache64 on 15/11/17.
 */

public abstract class BaseProgressManFragment extends BaseFragment {

    private ProgressManager progressMan;

    public ProgressManager getProgressMan() {
        return progressMan;
    }

    protected abstract int getMainViewID();

    public void setProgressMan(ProgressManager progressMan) {
        this.progressMan = progressMan;
    }

    protected abstract ProgressManager.Callback getProgressManCallback();
}
