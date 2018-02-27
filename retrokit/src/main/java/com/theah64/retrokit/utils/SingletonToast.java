package com.theah64.retrokit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by theapache64 on 27/2/18.
 */

public class SingletonToast {


    private static Toast toast;

    public static Toast makeText(final Context context, @StringRes final int message) {
        return makeText(context, context.getString(message), Toast.LENGTH_LONG);
    }

    public static Toast makeText(final Context context, @StringRes int message, int length) {
        return makeText(context, context.getString(message), length);
    }

    @SuppressLint("ShowToast")
    public static Toast makeText(final Context context, final String message, int length) {
        if (toast == null) {
            toast = Toast.makeText(context, message, length);
        }

        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(message);
        return toast;
    }
}
