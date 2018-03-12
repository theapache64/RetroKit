package com.theah64.retrokit.retro;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;

import com.theah64.retrokit.R;
import com.theah64.retrokit.activities.BaseProgressManActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 14/9/17.
 */

public abstract class CustomRetrofitCallback<T extends BaseAPIResponse<D>, D> implements Callback<T> {


    private static final String X = CustomRetrofitCallback.class.getSimpleName();

    @Nullable
    private final BaseProgressManActivity activity;


    protected CustomRetrofitCallback() {
        this(null, null);
    }

    protected CustomRetrofitCallback(@Nullable BaseProgressManActivity activity, String message) {
        this.activity = activity;
        if (activity != null) {
            activity.getProgressMan().showLoading(message);
        }
    }

    protected CustomRetrofitCallback(@Nullable BaseProgressManActivity activity, @StringRes int message) {
        this(activity, activity != null ? activity.getString(message) : null);
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

        final T resp = response.body();

        if (resp == null) {
            throw new IllegalArgumentException("Response is null");
        }


        if (activity != null && activity.isActive()) {
            activity.getProgressMan().showMainView();
        }

        if (resp.isError()) {

            if (activity != null && activity.isActive()) {
                activity.getDialogUtils().showErrorDialog(resp.getMessage());
            }

            final String errorMessage = resp.getMessage();
            Log.e(X, errorMessage);
            onFailure(errorMessage);
        } else {
            onSuccess(resp.getMessage(), resp.getData());
        }
    }

    protected abstract void onSuccess(String message, D data);


    protected void onFailure(String message) {

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        t.printStackTrace();
        if (activity != null && activity.isActive()) {
            activity.getProgressMan().showMainView();
            activity.getDialogUtils().showErrorDialog(R.string.network_error);
        }
        onFailure("Network error");
    }
}
