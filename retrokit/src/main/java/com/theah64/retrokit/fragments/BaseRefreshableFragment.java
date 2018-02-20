package com.theah64.retrokit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokit.utils.ProgressManager;

/**
 * Created by theapache64 on 15/11/17.
 */

public abstract class BaseRefreshableFragment<DATA> extends BaseProgressManFragment {
    private boolean isSetupCalled;

    public abstract void loadData();

    public boolean isRefreshable() {
        return true;
    }


    public boolean isSetupCalled() {
        return isSetupCalled;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isSetupCalled = false;
    }

    private SwipeRefreshLayout srl = null;

    protected void setup(final View layout) {

        setProgressMan(new ProgressManager(getActivity(), (ViewGroup) layout, getMainViewID(), getProgressManCallback(), null));

        this.isSetupCalled = true;

        if (isRefreshable()) {

            final View mainView = layout.findViewById(getMainViewID());
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
                    loadData();
                }

            });
        }


        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;

        if (getActionBarTitle() != null) {
            actionBar.setTitle(getActionBarTitle());
        }

        if (getActionBarSubTitle() != null) {
            actionBar.setSubtitle(getActionBarSubTitle());
        }

        loadData();

    }

    @Override
    public ProgressManager.Callback getProgressManCallback() {
        return new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData();
            }
        };
    }

    protected abstract String getActionBarSubTitle();

    protected abstract String getActionBarTitle();

}
