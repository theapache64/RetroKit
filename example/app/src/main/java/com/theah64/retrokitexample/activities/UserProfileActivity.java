package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.rest.APIInterface;
import com.theah64.retrokitexample.rest.RetrofitClient;
import com.theah64.retrokitexample.rest.responses.GetUserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends BaseDynamicActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithButterKnife(R.layout.activity_user_profile);

        final APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        apiInterface.getUserProfile().enqueue(new Callback<GetUserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetUserProfileResponse> call, @NonNull Response<GetUserProfileResponse> response) {
                System.out.println("User is : " + response.body().getUser());
            }

            @Override
            public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {
                Log.d(getLogTag(), "Failed");
            }
        });
    }
}
