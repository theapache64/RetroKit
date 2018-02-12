package com.theah64.retrokit.widgets;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by theapache64 on 29/1/18.
 */

public class NestedRadioGroup extends RadioGroup {

    private View clickedView;

    public NestedRadioGroup(Context context) {
        super(context);
    }

    public NestedRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    removeLayoutListenerPre16(getViewTreeObserver(), this);
                } else {
                    removeLayoutListenerPost16(getViewTreeObserver(), this);
                }
                init();
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void removeLayoutListenerPre16(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener listener) {
        observer.removeGlobalOnLayoutListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void removeLayoutListenerPost16(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener listener) {
        observer.removeOnGlobalLayoutListener(listener);
    }

    private final List<RadioButton> radioButtons = new ArrayList<>();

    private void init() {

        Log.d("NestedRadioGroup", "initializing nested radio groups");

        radioButtons.addAll(getAllRadioButtons(this));

        Log.d("NestedRadioGroup", "Found: " + radioButtons.size());

        for (final RadioButton rb : radioButtons) {

            Log.d("NestedRadioGroup", "Setting listener for : " + rb.getTag());
            rb.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked on :" + view.getTag());
                    clickedView = view;
                    clearCheck();
                }
            });
        }
    }

    @Override
    public int getCheckedRadioButtonId() {

        for (RadioButton rb : radioButtons) {
            if (rb.isChecked()) {
                return rb.getId();
            }
        }
        return -1;
    }

    public RadioButton getCheckedRadioButton() {
        for (RadioButton rb : radioButtons) {
            if (rb.isChecked()) {
                return rb;
            }
        }
        return null;
    }

    @Override
    public void clearCheck() {

        for (final RadioButton rb : radioButtons) {
            if (clickedView == null || rb.getId() != clickedView.getId()) {
                rb.setChecked(false);
            } else {
                rb.setChecked(true);
            }
        }

    }

    private Collection<? extends RadioButton> getAllRadioButtons(ViewGroup viewGroup) {
        final List<RadioButton> radioButtons = new ArrayList<>();
        Log.d("NestedRadioGroup", "Child count :" + viewGroup.getChildCount());
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View view = viewGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                Log.d("NestedRadioGroup", "Found radio button at " + i);
                radioButtons.add((RadioButton) view);
            } else if (view instanceof ViewGroup) {
                radioButtons.addAll(getAllRadioButtons((ViewGroup) view));
            }
        }
        return radioButtons;
    }
}
