package com.theah64.retrokitexample.utils;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.rest.APIInterface;
import com.wang.avi.indicators.BallPulseSyncIndicator;

/**
 * Created by theapache64 on 25/8/17.
 */

public class App extends Application {

    private static final String BASE_URL = "http://theapache64.com/mock_api/get_json/retrokit/";


    @Override
    public void onCreate() {
        super.onCreate();

        RetroKit.init(this, APIInterface.class, R.color.colorPrimary, R.color.colorPrimaryDark)
                .setRetrofitBaseURL(BASE_URL)
                .addIconModule(new FontAwesomeModule())
                .setDefaultFontPathAsRobotoRegular()
                .enableVersionCheck()
                .enableImageLoader()
                .enableNetworkLog()
                .setDefaultProgressIndicator(BallPulseSyncIndicator.class);
    }
}
