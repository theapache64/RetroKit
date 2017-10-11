package com.theah64.retrokitexample.utils;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokit.retro.RetrofitClient;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.rest.APIInterface;
import com.wang.avi.indicators.BallPulseSyncIndicator;

/**
 * Created by theapache64 on 25/8/17.
 */

public class App extends Application {

    private static final String BASE_URL = "http://18.220.163.253:8080/mock_api/get_json/retrokit/";

    public static APIInterface getApiInterface() {
        return RetrofitClient.getClient().create(APIInterface.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RetroKit.init(this)
                .setRetrofitBaseURL(BASE_URL)
                .addIconModule(new FontAwesomeModule())
                .setDefaultFontPathAsRobotoRegular()
                .setDebug(true)
                .setDefaultProgressIndicator(new BallPulseSyncIndicator())
                .setDefaultProgressIndicatorColor(R.color.colorPrimary);
    }
}
