package com.theah64.retrokitexample.rest;

import com.theah64.retrokitexample.rest.responses.GetUserProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by theapache64 on 25/8/17.
 */

public interface APIInterface {

    @GET("get_user_profile")
    Call<GetUserProfileResponse> getUserProfile();

}
