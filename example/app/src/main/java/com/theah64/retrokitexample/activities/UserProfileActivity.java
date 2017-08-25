package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.User;
import com.theah64.retrokitexample.rest.APIInterface;
import com.theah64.retrokitexample.rest.responses.GetUserProfileResponse;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends BaseDynamicActivity<GetUserProfileResponse> {

    @BindView(R.id.tvUserId)
    TextView tvUserId;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithButterKnife(R.layout.activity_user_profile);
        enableBackNavigationWithTitle("Profile");

        final APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        apiInterface.getUserProfile().enqueue(new Callback<GetUserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetUserProfileResponse> call, @NonNull Response<GetUserProfileResponse> response) {
                onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {
                Log.d(getLogTag(), "Failed");
            }
        });
    }


    @Override
    public void onSuccess(GetUserProfileResponse response) {
        final User user = response.getData().getUser();
        tvUserId.setText("#" + user.getId());
        tvUserName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }
}
