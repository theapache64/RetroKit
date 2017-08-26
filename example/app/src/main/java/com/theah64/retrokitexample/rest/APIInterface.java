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

    @GET("599ff1c62c0000dd0b51d600")
    Call<BaseAPIResponse<GetUserProfileData>> getUserProfile();

    @GET("59a02a742c0000220f51d6cd")
    Call<BaseAPIResponse<GetMoviesData>> getMovies();
}
