package com.theah64.retrokit.fragments;

import android.support.annotation.NonNull;

import com.theah64.bugmailer.core.BugMailer;
import com.theah64.retrokit.R;
import com.theah64.retrokit.exceptions.ServerException;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokit.utils.ProgressManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 15/11/17.
 */
public abstract class BaseDynamicFragment<DATA, APIINTERFACE> extends BaseRefreshableFragment<DATA> {

    private Call<BaseAPIResponse<DATA>> call;

    @Override
    public void loadData() {

        if (!isSetupCalled()) {
            throw new IllegalArgumentException("You should call setup() before calling loadData()");
        }

        if (call != null) {
            call.cancel();
        }

        getProgressMan().showLoading(getLoadingMessage());

        this.call = getCall((APIINTERFACE) RetrofitClient.getClient().create(RetroKit.getInstance().getApiInterface()));
        this.call.enqueue(new Callback<BaseAPIResponse<DATA>>() {
            @Override
            public void onResponse(@NonNull Call<BaseAPIResponse<DATA>> call, @NonNull final Response<BaseAPIResponse<DATA>> response) {
                if (response.body() != null) {
                    if (response.body().isError()) {
                        getProgressMan().showError(ProgressManager.ERROR_TYPE_SERVER_ERROR, response.body().getMessage());
                    } else {
                        onSuccess(response.body().getData());
                        getProgressMan().showMainView();
                    }
                } else {
                    BugMailer.INSTANCE.report(new ServerException());
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


    protected abstract void onSuccess(DATA data);

    protected abstract String getLoadingMessage();

    protected abstract Call<BaseAPIResponse<DATA>> getCall(APIINTERFACE apiInterface);

    @Override
    public void onStop() {
        if (this.call != null && !this.call.isCanceled()) {
            System.out.println("Pending call cancelled");
            this.call.cancel();
        }
        super.onStop();
    }
}

