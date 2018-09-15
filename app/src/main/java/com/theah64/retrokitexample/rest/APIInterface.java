package com.theah64.retrokitexample.rest;


import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.model.data.GetMoviesData;
import com.theah64.retrokitexample.model.data.GetUserProfileData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by theapache64 on 25/8/17.
 */

public interface APIInterface {

    @GET("get_user_profile")
    Call<BaseAPIResponse<GetUserProfileData>> getUserProfile();

    @GET("get_movies")
    Call<BaseAPIResponse<GetMoviesData>> getMovies();
}
