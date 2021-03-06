package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.User;
import com.theah64.retrokitexample.model.data.GetUserProfileData;
import com.theah64.retrokitexample.rest.APIInterface;

import butterknife.BindView;
import retrofit2.Call;

public class UserProfileActivity extends BaseDynamicActivity<GetUserProfileData, APIInterface> {

    @BindView(R.id.tvUserId)
    TextView tvUserId;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        enableBackNavigationWithTitle("Profile");

        setup();
    }

    @Override
    public int getMainViewID() {
        return R.id.llMain;
    }

    @Override
    protected String getActionBarSubTitle() {
        return null;
    }

    @Override
    protected String getActionBarTitle() {
        return null;
    }

    @Override
    protected void onSuccess(GetUserProfileData response, boolean isClearList) {
        final User user = response.getUser();
        tvUserId.setText("#" + user.getId());
        tvUserName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    @Override
    protected String getLoadingMessage() {
        return "Loading profile";
    }

    @Override
    protected Call<BaseAPIResponse<GetUserProfileData>> getCall(APIInterface apiInterface) {
        return apiInterface.getUserProfile();
    }
}
