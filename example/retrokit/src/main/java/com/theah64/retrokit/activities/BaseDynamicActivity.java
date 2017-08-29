package com.theah64.retrokit.activities;

import android.support.annotation.NonNull;

import com.theah64.retrokit.R;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.utils.ProgressManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 25/8/17.
 */

public abstract class BaseDynamicActivity<DATA, APIINTERFACE> extends BaseRefreshableActivity {

    public void loadData() {

        final ProgressManager pm = new ProgressManager(this, getMainViewID(), new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData();
            }
        });

        pm.showLoading(getLoadingMessage());

        getCall(getAPIInterface()).enqueue(new Callback<BaseAPIResponse<DATA>>() {
            @Override
            public void onResponse(@NonNull Call<BaseAPIResponse<DATA>> call, @NonNull final Response<BaseAPIResponse<DATA>> response) {
                if (response.body() != null) {
                    onSuccess(response.body());
                    pm.showMainView();
                } else {
                    pm.showError(ProgressManager.ERROR_TYPE_SERVER_ERROR, R.string.server_error);
                }


            }

            @Override
            public void onFailure(@NonNull Call<BaseAPIResponse<DATA>> call, Throwable t) {
                t.printStackTrace();
                pm.showError(ProgressManager.ERROR_TYPE_NETWORK_ERROR, R.string.network_error);
            }
        });
    }

    protected abstract void onSuccess(BaseAPIResponse<DATA> body);

    protected abstract int getMainViewID();

    protected abstract String getLoadingMessage();

    protected abstract APIINTERFACE getAPIInterface();

    protected abstract Call<BaseAPIResponse<DATA>> getCall(APIINTERFACE apiInterface);
}
