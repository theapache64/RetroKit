package com.theah64.retrokit.activities;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.utils.ProgressManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 25/8/17.
 */

public abstract class BaseDynamicActivity<R extends BaseAPIResponse<BaseAPIResponse>, A, D> extends BaseAppCompatActivity {

    public abstract void onSuccess(R response);

    public void loadData() {

        final ProgressManager pm = new ProgressManager(this, getMainViewID(), new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData();
            }
        });
        pm.showLoading(getLoadingMessage());

        getCall(getAPIInterface()).enqueue(new Callback<R>() {
            @Override
            public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                onSuccess(response.body());

                //TODO: Temp loading
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pm.showMainView();
                    }
                }, 3000);
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                Log.d(getLogTag(), "Failed");
                pm.showError(t.getMessage());
            }
        });
    }

    protected abstract int getMainViewID();

    protected abstract String getLoadingMessage();

    protected abstract A getAPIInterface();

    protected abstract Call<D> getCall(A apiInterface);
}
