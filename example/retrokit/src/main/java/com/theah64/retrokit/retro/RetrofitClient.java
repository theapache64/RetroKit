package com.theah64.retrokit.retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by theapache64 on 25/8/17.
 */

public class RetrofitClient {

    //private static final String BASE_URL = "http://theapache64.xyz:8080/mock_api/get_json/retrokitexample/";
    private static final String BASE_URL = "http://www.mocky.io/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
