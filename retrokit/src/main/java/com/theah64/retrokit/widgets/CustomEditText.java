package com.theah64.retrokit.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.theah64.retrokit.R;
import com.theah64.retrokit.widgets.utils.CustomWidgetUtils;

import java.util.regex.Pattern;

/**
 * Created by theapache64 on 15/9/17.
 */

public class CustomEditText extends AppCompatAutoCompleteTextView {

    private Pattern regExPattern;
    private int errorMessage;
    private CustomWidgetUtils cwUtils;

    public CustomEditText(Context context) {
        super(context);
        init(null);
    }


    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        cwUtils = new CustomWidgetUtils(
                R.styleable.CustomEditText,
                R.styleable.CustomEditText_iconLeft,
                R.styleable.CustomEditText_iconLeftColor,
                R.styleable.CustomEditText_iconLeftSize,
                R.styleable.CustomEditText_iconLeftPadding,
                -1,
                R.styleable.CustomEditText_isRequired).init(this, attrs);

    }

    public boolean isRequired() {
        return cwUtils.isRequired();
    }

    @Nullable
    public String getString() {
        final String data = getText().toString().trim();
        if (data.isEmpty()) {
            return null;
        }
        return data;
    }

    public void setRegEx(final String regEx) {
        this.regExPattern = Pattern.compile(regEx);
    }


    public void setRegEx(final Pattern regEx) {
        this.regExPattern = regEx;
    }

    public void setErrorMessage(@StringRes final int errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isMatch() {


        final String data = getString();

        if (data != null) {

            if (regExPattern != null) {

                //This will match or nothing
                final boolean isMatch = this.regExPattern.matcher(data).matches();

                if (!isMatch) {

                    //Invalid
                    if (errorMessage != -1) {
                        setError(getContext().getString(errorMessage));
                    } else {
                        throw new IllegalArgumentException("REGEX is set, but error message is " + errorMessage);
                    }

                    return false;

                } else {

                    return true;
                }
            } else {
                return true;
            }

        } else {
            setError(getContext().getString(R.string.Empty));
            return false;
        }


    }

    public void setRequired(boolean isRequired) {
        if (isRequired) {
            final IconDrawable icon = new IconDrawable(getContext(), FontAwesomeIcons.fa_asterisk).colorRes(R.color.red_500).sizeDp(6);
            setCompoundDrawables(icon, null, null, null);
            setCompoundDrawablePadding(10);
        }
    }


    /**
     * Created by shifar on 7/2/16.
     */
    public static final class EditTextValidator {

        private final Context context;
        private final CustomEditText[] customEditTexts;

        public EditTextValidator(Context context, CustomEditText... customEditTexts) {
            this.context = context;
            this.customEditTexts = customEditTexts;
        }


        public boolean isAllValid() {
            boolean isAllValid = true;
            for (final CustomEditText customEditText : customEditTexts) {
                isAllValid = customEditText.isMatch() && isAllValid;
            }

            if (!isAllValid) {
                Toast.makeText(context, R.string.Oops_validation_errors, Toast.LENGTH_SHORT).show();
            }

            return isAllValid;
        }

        /**
         * Used to clear all field's data.
         */
        public void clearFields() {
            for (final CustomEditText customEditText : customEditTexts) {
                customEditText.setText(null);
            }
        }

        public boolean isAtLeastOneValid() {

            for (final CustomEditText customEditText : customEditTexts) {
                if (customEditText.isMatch()) {
                    return true;
                }
            }

            return false;
        }
    }
}
