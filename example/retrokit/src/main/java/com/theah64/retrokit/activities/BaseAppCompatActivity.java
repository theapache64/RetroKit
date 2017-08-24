package com.theah64.retrokit.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by theapache64 on 24/8/17.
 */

public class BaseAppCompatActivity extends AppCompatActivity {


    public void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setContentViewWithButterKnife(int layoutId) {
        setContentView(layoutId);
        ButterKnife.bind(this);
    }
}
