package com.theah64.retrokit.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by theapache64 on 20/12/17.
 */

public class SpinnerBinder<M extends SpinnerBinder.SpinnerItem> {

    public interface SpinnerItem {
        String getMatchBy();
    }

    private final Context context;
    private final Spinner spinner;
    private final List<M> items;
    private Callback callback;
    private int selectionPosition;

    public SpinnerBinder(Context context, Spinner spinner, List<M> items) {
        this.context = context;
        this.spinner = spinner;
        this.items = items;
        this.selectionPosition = -1;
    }

    public SpinnerBinder<M> setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public SpinnerBinder<M> setSelectionBeforeBind(String matchingValue) {
        this.selectionPosition = getSelectionPosition(matchingValue);
        return this;
    }

    public int getSelectionPosition(String matchingValue) {

        if (matchingValue != null) {

            for (int i = 0; i < items.size(); i++) {
                final M item = items.get(i);
                final String itemProperty = item.getMatchBy();
                if (itemProperty != null && itemProperty.equals(matchingValue)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void setSelectionBy(String id) {
        spinner.setSelection(getSelectionPosition(id));
    }

    public void setSelectionBy(M model) {
        spinner.setSelection(getSelectionPosition(model.getMatchBy()));
    }

    public SpinnerBinder<M> bind() {

        final ArrayAdapter<M> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (callback != null) {
                    callback.onItemSelected(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (selectionPosition != -1) {
            spinner.setSelection(selectionPosition);
        }

        return this;
    }

    public interface Callback {
        void onItemSelected(int position);
    }
}
