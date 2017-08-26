package com.theah64.retrokit.activities;

import android.os.Handler;

import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.utils.ProgressManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 25/8/17.
 */

public abstract class BaseDynamicActivity<D, A> extends BaseAppCompatActivity {

    public void loadData() {

        final ProgressManager pm = new ProgressManager(this, getMainViewID(), new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData();
            }
        });
        pm.showLoading(getLoadingMessage());

        getCall(getAPIInterface()).enqueue(new Callback<BaseAPIResponse<D>>() {
            @Override
            public void onResponse(Call<BaseAPIResponse<D>> call, final Response<BaseAPIResponse<D>> response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.body().getData());
                        pm.showMainView();
                    }
                }, 1500);
            }

            @Override
            public void onFailure(Call<BaseAPIResponse<D>> call, Throwable t) {
                pm.showError(t.getMessage());
            }
        });
    }

    protected abstract void onSuccess(D body);

    protected abstract int getMainViewID();

    protected abstract String getLoadingMessage();

    protected abstract A getAPIInterface();

    protected abstract Call<BaseAPIResponse<D>> getCall(A apiInterface);
}
