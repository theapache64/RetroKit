package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokit.utils.ProgressManager;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.User;
import com.theah64.retrokitexample.rest.APIInterface;
import com.theah64.retrokitexample.rest.response.GetUserProfileResponse;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends BaseDynamicActivity {

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


        loadData();
    }

    public void loadData() {

        final ProgressManager pm = new ProgressManager(this, getMainViewID(), new ProgressManager.Callback() {
            @Override
            public void onRetryButtonClicked() {
                loadData();
            }
        });
        pm.showLoading(getLoadingMessage());

        final APIInterface apiInterface = RetrofitClient.getClient().create(APIInterface.class);
        apiInterface.getUserProfile().enqueue(new Callback<GetUserProfileResponse>() {
            @Override
            public void onResponse(Call<GetUserProfileResponse> call, final Response<GetUserProfileResponse> response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.body());
                        pm.showMainView();
                    }
                }, 1500);
            }

            @Override
            public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {
                pm.showError(t.getMessage());
            }
        });
    }


    public void onSuccess(GetUserProfileResponse response) {
        final User user = response.getData().getUser();
        tvUserId.setText("#" + user.getId());
        tvUserName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    @Override
    protected int getMainViewID() {
        return R.id.llMain;
    }

    @Override
    protected String getLoadingMessage() {
        return "Loading profile";
    }


}
