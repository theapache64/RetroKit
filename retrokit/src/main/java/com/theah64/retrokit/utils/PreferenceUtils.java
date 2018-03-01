package com.theah64.retrokit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by theapache64 on 12/9/17.
 */

public class PreferenceUtils {


    private static SharedPreferences pref;

    public static void init(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static SharedPreferences getPref() {
        return pref;
    }

    public static int getInt(String key) {
        return pref.getInt(key, -1);
    }

    public static void saveInt(String key, int value) {
        pref.edit().putInt(key, value).apply();
    }

    public static void saveString(String key, String value) {
        pref.edit().putString(key, value).apply();
    }

    public static void saveString(String key, int value) {
        pref.edit().putInt(key, value).apply();
    }

    public static String getString(String key) {
        return pref.getString(key, null);
    }
}
