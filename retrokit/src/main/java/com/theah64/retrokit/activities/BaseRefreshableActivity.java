package com.theah64.retrokit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewParent;

import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokit.utils.ProgressManager;

/**
 * Created by theapache64 on 29/8/17.
 */

public abstract class BaseRefreshableActivity<DATA> extends BaseProgressManActivity {

    private boolean isSetupCalled;

    public abstract void loadData(boolean isClearList);

    public boolean isRefreshable() {
        return false;
    }


    public boolean isSetupCalled() {
        return isSetupCalled;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isSetupCalled = false;
    }

    private SwipeRefreshLayout srl = null;

    protected void setup() {

        setProgressMan(new ProgressManager(this, getMainViewID(), getProgressManCallback()));

        this.isSetupCalled = true;

        if (isRefreshable()) {

            final View mainView = findViewById(getMainViewID());
            if (mainView instanceof SwipeRefreshLayout) {
                srl = (SwipeRefreshLayout) mainView;
            } else {
                ViewParent view = mainView.getParent();
                while (!(view instanceof SwipeRefreshLayout)) {
                    view = view.getParent();
                }

                srl = (SwipeRefreshLayout) view;
            }


            srl.setColorSchemeResources(RetroKit.getInstance().getColorPrimary(), RetroKit.getInstance().getColorPrimaryDark());
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    srl.setRefreshing(false);
                    loadData(true);
                }

            });
        }


        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        if (getActionBarTitle() != null) {
            actionBar.setTitle(getActionBarTitle());
        }

        if (getActionBarSubTitle() != null) {
            actionBar.setSubtitle(getActionBarSubTitle());
        }

        loadData(true);

    }

    @Override
    public ProgressManager.Callback getProgressManCallback() {
        return new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData(true);
            }
        };
    }

    protected String getActionBarSubTitle(){
        return null;
    }

    protected String getActionBarTitle(){
        return null;
    }


}
