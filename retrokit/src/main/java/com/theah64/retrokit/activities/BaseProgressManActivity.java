package com.theah64.retrokit.activities;

import com.theah64.retrokit.utils.ProgressManager;

/**
 * Created by theapache64 on 19/10/17.
 */

public abstract class BaseProgressManActivity extends BaseAppCompatActivity {

    ProgressManager progressMan;

    public ProgressManager getProgressMan() {
        return progressMan;
    }

    protected abstract int getMainViewID();

    public void setProgressMan(ProgressManager progressMan) {
        this.progressMan = progressMan;
    }

    protected abstract ProgressManager.Callback getProgressManCallback();
}
