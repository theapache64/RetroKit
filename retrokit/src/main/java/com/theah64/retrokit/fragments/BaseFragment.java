package com.theah64.retrokit.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by theapache64 on 15/11/17.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @LayoutRes
    public abstract int getLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View layout = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, layout);
        onBindData(layout);
        return layout;
    }

    protected abstract void onBindData(View layout);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
