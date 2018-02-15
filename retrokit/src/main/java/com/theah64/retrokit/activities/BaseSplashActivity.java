package com.theah64.retrokit.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.theah64.retrokit.R;
import com.theah64.retrokit.retro.RetroKit;


/**
 * Created by theapache64 on 20/8/17.
 */

public abstract class BaseSplashActivity extends BaseAppCompatActivity {

    public abstract void onSplashStart();

    public abstract long getSplashDuration();

    public abstract void onSplashFinished();

    @DrawableRes
    public int getLogo() {
        return -1;
    }

    @StringRes
    public int getTextLogo() {
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getLogo() != -1) {
            ((ImageView) findViewById(R.id.ivSplashLogo)).setImageResource(getLogo());
        }


        if (getTextLogo() != -1) {
            final TextView tvTextLogo = (TextView) findViewById(R.id.tvTextLogo);
            tvTextLogo.setText(getTextLogo());
            tvTextLogo.setTextSize(getTextLogoSize());
            tvTextLogo.setTextColor(ContextCompat.getColor(this, getTextLogoColor()));
        }

        ((ImageView) findViewById(R.id.ivBg)).setVisibility(isWhitePatternBg() ? View.VISIBLE : View.GONE);

        startSplashEngine();
    }

    protected void startSplashEngine() {
        onSplashStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onSplashFinished();
            }
        }, getSplashDuration());
    }

    public float getTextLogoSize() {
        return 18f;
    }

    public int getTextLogoColor() {
        return RetroKit.getInstance().getColorPrimary();
    }

    public boolean isWhitePatternBg() {
        return false;
    }
}
