package com.theah64.retrokit.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.theah64.retrokit.R;
import com.theah64.retrokit.retro.RetroKit;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Created by shifar on 23/7/16.
 */
public class ProgressManager {

    private final View progressLayout, mainView, tvErrorIcon;
    private AVLoadingIndicatorView pbLoading;
    private final Button bRetry;
    private final TextView tvMessage;
    private final Context context;
    @Nullable
    private final Callback callback;
    private final String retryButtonText;
    private boolean isLoading, isShowingError;


    public ProgressManager(Activity activity, final int mainViewId, @Nullable final Callback callback) {
        this(activity, (ViewGroup) activity.getWindow().getDecorView().getRootView(), mainViewId, callback, null);
    }

    public ProgressManager(final Activity activity, final ViewGroup rootLayout, @IdRes final int mainViewId, final Callback callback, @Nullable String retryButtonText) {
        this.context = activity;
        this.callback = callback;
        this.retryButtonText = retryButtonText;

        mainView = rootLayout.findViewById(mainViewId);

        if (mainView == null) {
            throw new IllegalArgumentException("main_view couldn't found in the given layout ");
        }

        progressLayout = LayoutInflater.from(activity).inflate(R.layout.progress_layout, rootLayout, false);
        ((ViewGroup) mainView.getParent()).addView(progressLayout);

        tvErrorIcon = progressLayout.findViewById(R.id.ivErrorIcon);
        pbLoading = (AVLoadingIndicatorView) progressLayout.findViewById(R.id.pbLoading);

        final RetroKit retroKit = RetroKit.getInstance();

        pbLoading.setIndicator(retroKit.getDefaultProgressIndicator());
        pbLoading.setIndicatorColor(ContextCompat.getColor(context, retroKit.getDefaultProgressIndicatorColor()));

        tvMessage = (TextView) progressLayout.findViewById(R.id.tvMessage);
        bRetry = (Button) progressLayout.findViewById(R.id.bRetry);
        bRetry.setText(retryButtonText != null ? retryButtonText : activity.getString(R.string.RETRY));

        if (callback != null) {
            bRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onRetryButtonClicked();
                }
            });
        }

        isLoading = false;
        isShowingError = false;
    }

    public void showError(String message) {
        isLoading = false;
        isShowingError = true;
        pbLoading.hide();
        mainView.setVisibility(View.INVISIBLE);
        progressLayout.setVisibility(View.VISIBLE);
        tvErrorIcon.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(message);
        bRetry.setVisibility(View.VISIBLE);
    }

    public void showError(int message) {
        showError(context.getString(message));
    }

    public void showLoading(final String message) {
        isLoading = true;
        isShowingError = false;
        pbLoading.show();
        mainView.setVisibility(View.INVISIBLE);
        progressLayout.setVisibility(View.VISIBLE);
        tvErrorIcon.setVisibility(View.GONE);
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(message == null ? "" : message);
        bRetry.setVisibility(View.GONE);
    }

    public void showLoading(final int message) {
        showLoading(context.getString(message));
    }

    public void showMainView() {
        isLoading = false;
        isShowingError = false;
        mainView.setVisibility(View.VISIBLE);
        progressLayout.setVisibility(View.INVISIBLE);
        bRetry.setVisibility(View.GONE);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isShowingError() {
        return isShowingError;
    }


    public interface Callback {
        void onRetryButtonClicked();
    }
}