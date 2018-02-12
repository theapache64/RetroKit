package com.theah64.retrokit.utils;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by theapache64 on 16/11/17.
 */

public class PermissionUtils {

    public final int requestCode;
    private final String[] permissionsNeeded;

    private final Context context;
    private final Callback callback;
    private final AppCompatActivity activity;

    public PermissionUtils(int requestCode, @NotNull Context context, String[] permissionsNeeded, @NotNull Callback callback, @Nullable AppCompatActivity activity) {
        this.requestCode = requestCode;
        this.context = context;
        this.permissionsNeeded = permissionsNeeded;
        this.callback = callback;
        this.activity = activity;
    }

    public PermissionUtils(int requestCode, @NotNull Context context, String[] permissionsNeeded, @NotNull Callback callback) {
        this(requestCode, context, permissionsNeeded, callback, null);
    }


    public PermissionUtils ask() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            boolean isAllPermissionAccepted = true;
            for (final String perm : permissionsNeeded) {
                if (ActivityCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                    isAllPermissionAccepted = false;
                    break;
                }
            }

            if (!isAllPermissionAccepted) {
                if (activity != null) {
                    activity.requestPermissions(permissionsNeeded, requestCode);
                } else {
                    callback.onPermissionRefused();
                }
            } else {
                callback.onPermissionGranted();
            }

        } else {
            callback.onPermissionGranted();
        }

        return this;
    }

    public void handleResult(final int requestCode, int[] grantResults) {

        if (this.requestCode == requestCode) {

            boolean isAllPermissionGranted = true;

            for (final int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isAllPermissionGranted = false;
                    break;
                }
            }

            if (isAllPermissionGranted) {
                callback.onPermissionGranted();
            } else {
                callback.onPermissionRefused();
            }

        }
    }


    public interface Callback {
        void onPermissionGranted();

        void onPermissionRefused();
    }
}
