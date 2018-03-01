package com.theah64.retrokit.utils;

import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by theapache64 on 28/2/18.
 */

public class RupeeUtils {
    private static final String PRIMARY_AMOUNT_FORMAT = "{fa-rupee 17sp @color/grey_400} %s";

    public static void setRupeeText(IconTextView itv, String cost) {
        itv.setText(String.format(PRIMARY_AMOUNT_FORMAT, cost));
    }
}
