package com.theah64.retrokit.utils;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theah64.retrokit.R;


/**
 * Created by theapache64 on 19/2/18.
 */

public class EmptyDataManager {


    private final View emptyDataLayout;

    public EmptyDataManager(Activity activity, int containerId, @DrawableRes int image, @StringRes int title, @StringRes int subTitle) {

        final ViewGroup container = activity.findViewById(containerId);
        final LayoutInflater inflater = LayoutInflater.from(activity);

        this.emptyDataLayout = inflater.inflate(R.layout.empty_data, container, false);
        emptyDataLayout.setVisibility(View.GONE);

        final ImageView ivNoDataImage = emptyDataLayout.findViewById(R.id.ivNoDataImage);
        final TextView tvNoDataTitle = emptyDataLayout.findViewById(R.id.tvNoDataTitle);
        final TextView tvNoDataSubTitle = emptyDataLayout.findViewById(R.id.tvNoDataSubTitle);

        ivNoDataImage.setImageResource(image);
        tvNoDataTitle.setText(title);
        tvNoDataSubTitle.setText(subTitle);

        container.addView(emptyDataLayout);

    }

    public void showEmpty() {
        emptyDataLayout.setVisibility(View.VISIBLE);
    }

    public void hideEmpty() {
        emptyDataLayout.setVisibility(View.GONE);
    }

}
