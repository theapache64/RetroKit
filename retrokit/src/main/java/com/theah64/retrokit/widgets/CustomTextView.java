package com.theah64.retrokit.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.theah64.retrokit.R;
import com.theah64.retrokit.widgets.utils.CustomWidgetUtils;

/**
 * Created by theapache64 on 15/9/17.
 */

public class CustomTextView extends AppCompatTextView {

    private CustomWidgetUtils customWidgetUtils;

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        customWidgetUtils = new CustomWidgetUtils(
                R.styleable.CustomTextView,
                R.styleable.CustomTextView_iconLeft,
                R.styleable.CustomTextView_iconLeftColor,
                R.styleable.CustomTextView_iconLeftSize,
                R.styleable.CustomTextView_iconLeftPadding,
                R.styleable.CustomTextView_isStrikeThrough,
                -1).init(this, attrs);
    }

    public CustomWidgetUtils getCustomWidgetUtils() {
        return customWidgetUtils;
    }

    public static void setTextAdvanced(TextView textView, String data) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(data);
        YoYo.with(Techniques.Tada).duration(300).playOn(textView);
    }
}
