package com.theah64.retrokit.activities;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.theah64.retrokit.R;
import com.theah64.retrokit.utils.DialogUtils;
import com.theah64.retrokit.utils.PreferenceUtils;
import com.theah64.retrokit.widgets.CustomSpinner;
import com.theah64.retrokit.widgets.ValidTextInputLayout;

import java.io.Serializable;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by theapache64 on 24/8/17.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {


    private DialogUtils dialogUtils;

    boolean active = false;

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    public void enableBackNavigation() {
        configActionbar(null, null, true);
    }

    protected void enableBackNavigationWithTitle(final String title) {
        configActionbar(title, null, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogUtils = new DialogUtils(this);
    }

    protected void configActionbar(@Nullable final String title, @Nullable final String subtitle, boolean isBackNav) {
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        if (isBackNav) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (title != null) {
            actionBar.setTitle(title);
        }

        actionBar.setSubtitle(subtitle);
    }

    protected void configActionbar(@StringRes final int title, @StringRes final int subtitle, boolean isBackNav) {
        String sTitle = null, subTitle = null;
        if (title != -1) {
            sTitle = getString(title);
        }

        if (subtitle != -1) {
            subTitle = getString(subtitle);
        }

        configActionbar(sTitle, subTitle, isBackNav);
    }

    //Enabling back navigation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Close this activity
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public String getLogTag() {
        return this.getClass().getSimpleName();
    }

    public DialogUtils getDialogUtils() {
        return dialogUtils;
    }

    protected void openUrl(final String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    protected final void openGMaps(String lat, String lon) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(String.format("http://maps.google.com/maps?daddr=%s,%s", lat, lon)));
        startActivity(intent);
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void toast(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void savePref(String key, String value) {
        PreferenceUtils.saveString(key, value);
    }

    protected String getPref(String key) {
        return PreferenceUtils.getString(key);
    }


    public String getStringOrThrow(String key) {
        String data = getString(key);
        if (data == null) {
            throw new IllegalArgumentException("No value found for " + key);
        }
        return data;
    }

    public String getString(String key) {
        return getIntent().getStringExtra(key);
    }

    public int getIntOrThrow(String key) {
        int data = getIntent().getIntExtra(key, -1);
        if (data == -1) {
            throw new IllegalArgumentException("No value found for " + key);
        }
        return data;
    }

    public Serializable getSerializableOrThrow(String key) {
        Serializable s = getIntent().getSerializableExtra(key);
        if (s == null) {
            throw new IllegalArgumentException("No serializable found with key " + key);
        }
        return s;
    }

    protected void confirm(View.OnClickListener onYesCallback) {
        Snackbar.make(getWindow().getDecorView().getRootView(), R.string.Are_you_sure, Snackbar.LENGTH_LONG)
                .setAction(R.string.YES, onYesCallback)
                .show();
    }


    private void resetFields(ViewGroup rootView) {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (child instanceof ValidTextInputLayout) {
                final ValidTextInputLayout vtil = (ValidTextInputLayout) child;
                YoYo.with(Techniques.Shake).duration(500).onStart(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        vtil.setText(null);
                        vtil.setErrorEnabled(false);
                    }
                }).playOn(child);

            } else if (child instanceof CustomSpinner) {
                final CustomSpinner csp = (CustomSpinner) child;
                YoYo.with(Techniques.Shake).duration(500).onStart(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        csp.setSelection(0);
                    }
                }).playOn(child);
            } else if (child instanceof ViewGroup) {
                resetFields((ViewGroup) child);
            }

        }
    }

    public void resetFields() {
        resetFields((ViewGroup) getWindow().getDecorView());
    }

    public static void start(Context context, Class<? extends BaseAppCompatActivity> actToLaunch) {
        context.startActivity(new Intent(context, actToLaunch));
    }

    public void onBackPressed(View v) {
        onBackPressed();
    }
}
