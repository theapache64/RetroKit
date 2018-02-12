package com.theah64.retrokit.activities;

import android.support.annotation.NonNull;

import com.theah64.retrokit.R;
import com.theah64.retrokit.callbacks.OnErrorTrueCallback;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokit.utils.ProgressManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 25/8/17.
 */

public abstract class BaseDynamicActivity<DATA, APIINTERFACE> extends BaseRefreshableActivity<DATA> {

    private DATA response;
    private Call<BaseAPIResponse<DATA>> call;


    @Override
    public void loadData(final boolean isClearList) {

        if (!isSetupCalled()) {
            throw new IllegalArgumentException("You should call setup() before calling loadData()");
        }

        if (call != null) {
            call.cancel();
        }


        getProgressMan().showLoading(getLoadingMessage());

        this.call = getCall((APIINTERFACE) RetrofitClient.getClient().create(RetroKit.getInstance().getApiInterface()));
        if (this.call != null) {
            this.call.enqueue(new Callback<BaseAPIResponse<DATA>>() {
                @Override
                public void onResponse(@NonNull Call<BaseAPIResponse<DATA>> call, @NonNull final Response<BaseAPIResponse<DATA>> response) {
                    if (response.body() != null) {
                        if (response.body().isError()) {
                            final String message = response.body().getMessage();

                            //Checking if custom error handling enabled
                            final String errorMessage = RetroKit.getInstance().getErrorMessage();
                            final OnErrorTrueCallback errorCallback = RetroKit.getInstance().getErrorCallback();

                            if (errorMessage != null && errorCallback != null && errorMessage.equals(message)) {
                                errorCallback.onError(BaseDynamicActivity.this);
                            } else {
                                onErrorTrue(message);
                            }

                        } else {
                            BaseDynamicActivity.this.response = response.body().getData();
                            onSuccess(response.body().getData(), isClearList);
                            getProgressMan().showMainView();
                        }
                    } else {
                        getProgressMan().showError(ProgressManager.ERROR_TYPE_SERVER_ERROR, R.string.server_error);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<BaseAPIResponse<DATA>> call, Throwable t) {
                    t.printStackTrace();
                    if (!call.isCanceled()) {
                        getProgressMan().showError(ProgressManager.ERROR_TYPE_NETWORK_ERROR, R.string.network_error);
                    }
                }
            });
        }

    }

    protected void onErrorTrue(String message) {
        getProgressMan().showError(ProgressManager.ERROR_TYPE_SERVER_ERROR, message);
    }


    public DATA getResponse() {
        return response;
    }

    protected abstract void onSuccess(DATA response, boolean isClearList);

    protected String getLoadingMessage() {
        return getString(R.string.Loading);
    }


    protected abstract Call<BaseAPIResponse<DATA>> getCall(APIINTERFACE apiInterface);

    @Override
    protected void onStop() {
        super.onStop();
        if (this.call != null && !this.call.isCanceled()) {
            System.out.println("Pending call cancelled");
            this.call.cancel();
        }
    }


}
