package com.theah64.retrokit.widgets.utils;

import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;
import com.theah64.retrokit.R;


/**
 * Created by theapache64 on 15/9/17.
 */
public class CustomWidgetUtils {

    private final int[] styleableRes;
    private final int iconLeftRes;
    private final int iconLeftColorRes;
    private final int iconLeftSizeRes;
    private final int iconLeftPaddingRes;
    private final int isStrikeThroughRes;
    private final int isRequiredRes;
    private boolean isRequired;
    private TextView iconView;

    private int iconLeftColor;
    private int iconLeftSize;
    private int iconLeftPadding;

    public CustomWidgetUtils(int[] styleableRes, int iconLeftRes, int iconLeftColorRes, int iconLeftSizeRes, int iconLeftPaddingRes, int isStrikeThroughRes, int isRequired) {
        this.styleableRes = styleableRes;
        this.iconLeftRes = iconLeftRes;
        this.iconLeftColorRes = iconLeftColorRes;
        this.iconLeftSizeRes = iconLeftSizeRes;
        this.iconLeftPaddingRes = iconLeftPaddingRes;
        this.isStrikeThroughRes = isStrikeThroughRes;
        this.isRequiredRes = isRequired;
    }

    public CustomWidgetUtils init(final TextView iconView, @Nullable AttributeSet attrs) {

        this.iconView = iconView;

        if (attrs != null) {

            //Collecting custom attrs
            TypedArray ta = iconView.getContext().obtainStyledAttributes(attrs, styleableRes, 0, 0);

            if (!iconView.isInEditMode()) {

                String iconLeft = ta.getString(iconLeftRes);
                if (isRequiredRes != -1) {
                    this.isRequired = ta.getBoolean(isRequiredRes, false);
                }

                iconLeftColor = -1;
                iconLeftSize = -1;
                iconLeftPadding = -1;

                if (iconLeft != null) {
                    iconLeftColor = ta.getColor(iconLeftColorRes, ContextCompat.getColor(iconView.getContext(), R.color.grey_300));
                    iconLeftSize = (int) ta.getDimension(iconLeftSizeRes, 15);
                    iconLeftPadding = (int) ta.getDimension(iconLeftPaddingRes, 10);
                } else if (isRequired) {
                    iconLeft = iconView.getContext().getString(R.string.fa_asterisk);
                    iconLeftColor = ContextCompat.getColor(iconView.getContext(), R.color.red_500);
                    iconLeftSize = 6;
                    iconLeftPadding = 10;
                }

                if (iconLeft != null) {
                    final IconDrawable icon = new IconDrawable(iconView.getContext(), iconLeft).color(iconLeftColor).sizeDp(iconLeftSize);
                    iconView.setCompoundDrawables(icon, null, null, null);
                    iconView.setCompoundDrawablePadding(iconLeftPadding);
                }
            }


            if (isStrikeThroughRes != -1) {
                final boolean isStrikeThrough = ta.getBoolean(isStrikeThroughRes, false);
                if (isStrikeThrough) {
                    iconView.setPaintFlags(iconView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }

            ta.recycle();
        }

        return this;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setIconLeft(Icon iconRes) {
        final IconDrawable icon = new IconDrawable(iconView.getContext(), iconRes)
                .color(iconLeftColor)
                .sizeDp(iconLeftSize);
        iconView.setCompoundDrawables(icon, null, null, null);
        iconView.setCompoundDrawablePadding(iconLeftPadding);
    }
}
