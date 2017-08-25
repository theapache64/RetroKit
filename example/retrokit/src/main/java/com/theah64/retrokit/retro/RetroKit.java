package com.theah64.retrokit.retro;

import com.theah64.retrokit.R;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.BallPulseSyncIndicator;

/**
 * Created by theapache64 on 25/8/17.
 */

public class RetroKit {

    private static final RetroKit instance = new RetroKit();
    private Indicator defaultProgressIndicator = new BallPulseSyncIndicator();
    private int defaultProgressIndicatorColor = R.color.primary;

    public static RetroKit getInstance() {
        return instance;
    }

    public RetroKit setRetrofitBaseURL(String baseUrl) {
        RetrofitClient.setBaseUrl(baseUrl);
        return this;
    }

    public RetroKit setDefaultProgressIndicator(Indicator defaultProgressIndicator) {
        this.defaultProgressIndicator = defaultProgressIndicator;
        return this;
    }

    public RetroKit setDefaultProgressIndicatorColor(int defaultProgressIndicatorColor) {
        this.defaultProgressIndicatorColor = defaultProgressIndicatorColor;
        return this;
    }

    public Indicator getDefaultProgressIndicator() {
        return defaultProgressIndicator;
    }

    public int getDefaultProgressIndicatorColor() {
        return defaultProgressIndicatorColor;
    }
}
