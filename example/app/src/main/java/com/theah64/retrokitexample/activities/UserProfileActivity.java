package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.User;
import com.theah64.retrokitexample.rest.APIInterface;
import com.theah64.retrokitexample.rest.responses.GetUserProfileResponse;

import butterknife.BindView;
import retrofit2.Call;

public class UserProfileActivity extends BaseDynamicActivity<GetUserProfileResponse, APIInterface> {

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


    @Override
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

    @Override
    protected APIInterface getAPIInterface() {
        return RetrofitClient.getClient().create(APIInterface.class);
    }


    @Override
    protected Call<GetUserProfileResponse> getCall(APIInterface apiInterface) {
        return apiInterface.getUserProfile();
    }


}
