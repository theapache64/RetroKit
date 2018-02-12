package com.theah64.retrokit.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.theah64.retrokit.R;
import com.theah64.retrokit.utils.SpinnerBinder;

import java.util.List;


/**
 * Created by theapache64 on 25/4/17.
 */

public class CustomSpinner<M extends SpinnerBinder.SpinnerItem> extends android.support.v7.widget.AppCompatSpinner {

    private String errorMessage;
    private boolean isRequired;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getErrorMessage() {
        if (errorMessage != null) {
            return errorMessage;
        }
        return getResources().getString(R.string.Please_choose_one);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void bind(List<M> series, @Nullable M placeholder) {
        bind(series, placeholder, (String) null);
    }

    public SpinnerBinder<M> bind(List<M> series, @Nullable M placeholder, String matchingValue) {

        if (placeholder != null) {
            series.add(0, placeholder);
        } else if (isRequired) {
            //Required but no placeholder passed
            throw new IllegalArgumentException("Missing placeholder for a required spinner");
        }


        return new SpinnerBinder<M>(getContext(), this, series)
                .setSelectionBeforeBind(matchingValue)
                .bind();
    }


    public SpinnerBinder<M> bind(List<M> series, @Nullable M placeholder, M matchingValue) {
        return bind(series, placeholder, matchingValue.getMatchBy());
    }

    public SpinnerBinder<M> bind(List<M> series) {
        return bind(series, null, (String) null);
    }

    @SuppressWarnings("unchecked")
    public M getItemSelected() {
        return ((M) getSelectedItem());
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired;
    }
}
