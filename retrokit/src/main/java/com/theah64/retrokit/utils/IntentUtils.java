package com.theah64.retrokit.utils;

import android.content.Context;
import android.content.Intent;

import com.theah64.retrokit.R;

/**
 * Created by theapache64 on 7/3/18.
 */

public class IntentUtils {
    public static void shareText(Context context, String subject, String text) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(sharingIntent, context.getResources().getString(R.string.Share_using)));
    }
}
