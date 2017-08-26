package com.theah64.retrokitexample.utils;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.theah64.retrokit.retro.RetroKit;
import com.theah64.retrokitexample.R;
import com.wang.avi.indicators.BallPulseSyncIndicator;

/**
 * Created by theapache64 on 25/8/17.
 */

public class App extends Application {

    private static final String BASE_URL = "http://www.moc1ky.io/v2/";

    @Override
    public void onCreate() {
        super.onCreate();

        RetroKit.getInstance()
                .setRetrofitBaseURL(BASE_URL)
                .setIconModule(new FontAwesomeModule())
                .setDefaultFontPathAsRobotoRegular()
                .setDefaultProgressIndicator(new BallPulseSyncIndicator())
                .setDefaultProgressIndicatorColor(R.color.colorPrimary);
    }
}
