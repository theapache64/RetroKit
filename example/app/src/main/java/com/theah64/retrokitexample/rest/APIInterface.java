package com.theah64.retrokitexample.rest;

import com.theah64.retrokitexample.rest.responses.GetUserProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by theapache64 on 25/8/17.
 */

public interface APIInterface {

    @GET("599ff1c62c0000dd0b51d600")
    Call<GetUserProfileResponse> getUserProfile();

    //@GET("59a02a742c0000220f51d6cd")

}
