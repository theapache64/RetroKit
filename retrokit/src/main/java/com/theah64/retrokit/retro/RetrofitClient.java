package com.theah64.retrokit.retro;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by theapache64 on 25/8/17.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private static String baseUrl;

    public static void setBaseUrl(String baseUrl) {
        RetrofitClient.baseUrl = baseUrl;
    }

    public static Retrofit getClient() {

        if (baseUrl == null) {
            throw new IllegalArgumentException("Call RetroKit.init() from your Application class");
        }

        if (retrofit == null) {


            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());

            if (RetroKit.getInstance().isLogNetwork()) {

                System.out.println("Enabled network logging");

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(logging);

                retrofitBuilder.client(httpClient.build());
            }

            retrofit = retrofitBuilder.build();
        }

        return retrofit;
    }


}