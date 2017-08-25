package com.theah64.retrokit.activities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokit.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 25/8/17.
 */

public abstract class BaseDynamicActivity<R extends BaseAPIResponse> extends BaseAppCompatActivity {
    public abstract void onSuccess(R response);
}
