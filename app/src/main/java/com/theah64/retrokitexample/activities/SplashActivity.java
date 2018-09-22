package com.theah64.retrokitexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.theah64.retrokit.activities.BaseSplashActivity;
import com.theah64.retrokitexample.R;

public class SplashActivity extends BaseSplashActivity {

    @Override
    public int getTextLogo() {
        return R.string.Retrokit_Example;
    }

    @Override
    public float getTextLogoSize() {
        return 38;
    }

    @Override
    public boolean isWhitePatternBg() {
        return true;
    }

    @Override
    public void onSplashStart() {
        // not used
    }

    @Override
    public long getSplashDuration() {
        return 2000;
    }

    @Override
    public void onSplashFinished() {
        final Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
