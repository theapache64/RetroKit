package com.theah64.retrokit.widgets;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.theah64.retrokit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by theapache64 on 7/2/16.
 */
public final class ValidTextInputLayout<M> extends TextInputLayout {

    public static final String REGEX_INDIAN_MOBILE = "^[789]\\d{9}$";
    public static final String REGEX_EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
    private CustomEditText et;
    private Pattern regExPattern;
    private int errorMessage = -1;
    private ArrayAdapter<M> sugAdapter;

    public ValidTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    private void initEt() {
        if (this.et == null) {
            this.et = (CustomEditText) getEditText();
            if (this.et == null) {
                throw new IllegalArgumentException("No EditText child found");
            }
        }
    }

    public void setRegEx(final String regEx) {
        this.regExPattern = Pattern.compile(regEx);
    }

    public void setErrorMessage(@StringRes final int errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isMatch() {

        initEt();

        if (et != null) {

            final String data = et.getString();

            if (data != null) {

                if (regExPattern != null) {

                    //This will match or nothing
                    final boolean isMatch = this.regExPattern.matcher(data).matches();

                    if (!isMatch) {

                        //Invalid
                        if (errorMessage != -1) {
                            setErrorEnabled(true);
                            setError(getContext().getString(errorMessage));
                        } else {
                            throw new IllegalArgumentException("REGEX is set, but error message is " + errorMessage);
                        }


                        return false;
                    } else {
                        //Valid
                        setErrorEnabled(false);
                        return true;
                    }
                } else {
                    //No regex is not , set it's valid
                    setErrorEnabled(false);
                    return true;
                }

            } else {
                //Data is empty
                setErrorEnabled(true);
                setError(getContext().getString(R.string.Empty));
                return false;
            }

        } else {
            throw new IllegalArgumentException("TextInputLayout doesn't has an EditText to validate");
        }

    }

    public void setText(String text) {
        initEt();
        et.setText(text);
    }

    public String getString() {
        initEt();
        return et.getString();
    }

    public void enableEmailValidation() {
        setRegEx(ValidTextInputLayout.REGEX_EMAIL);
        setErrorMessage(R.string.Invalid_email);
    }

    public void setSuggestion(List<M> suggestion, final SuggestionCallback<M> callback) {

        this.sugAdapter = new ArrayAdapter<M>
                (getContext(), R.layout.actv_row, suggestion);
        sugAdapter.setDropDownViewResource(R.layout.spinner_item);
        final CustomEditText cet = (CustomEditText) getEditText();
        assert cet != null;
        cet.setThreshold(1);
        cet.setAdapter(sugAdapter);
        cet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                final M paymentTerm = (M) parent.getItemAtPosition(position);
                callback.onSuggestionClicked(paymentTerm);
            }
        });
    }

    public void addSuggestion(M m) {
        this.sugAdapter.insert(m, 0);
        this.sugAdapter.notifyDataSetChanged();
    }


    public interface SuggestionCallback<M> {
        void onSuggestionClicked(M m);
    }


    /**
     * Created by shifar on 7/2/16.
     */
    public static final class InputValidator {

        private final ValidTextInputLayout[] validInputLayouts;

        public InputValidator(ValidTextInputLayout... validInputLayouts) {
            this.validInputLayouts = validInputLayouts;
            for (final ValidTextInputLayout vtil : validInputLayouts) {
                System.out.println("REQ: " + vtil.getHint());
            }
        }

        public InputValidator(Activity activity) {
            this((ValidTextInputLayout[]) getValidTextInputLayouts((ViewGroup) activity.getWindow().getDecorView()).toArray(new ValidTextInputLayout[]{}));
        }

        public InputValidator(ViewGroup viewGroup) {
            this((ValidTextInputLayout[]) getValidTextInputLayouts(viewGroup).toArray(new ValidTextInputLayout[]{}));
        }


        private static List<ValidTextInputLayout> getValidTextInputLayouts(ViewGroup rootView) {

            final List<ValidTextInputLayout> validTextInputLayouts = new ArrayList<>();

            for (int i = 0; i < rootView.getChildCount(); i++) {
                View child = rootView.getChildAt(i);
                if (child instanceof ValidTextInputLayout) {
                    final ValidTextInputLayout vtil = ((ValidTextInputLayout) child);
                    final CustomEditText cet = (CustomEditText) vtil.getEditText();
                    assert cet != null;
                    if (cet.isRequired()) {
                        validTextInputLayouts.add(vtil);
                    }
                } else if (child instanceof ViewGroup) {
                    validTextInputLayouts.addAll(getValidTextInputLayouts((ViewGroup) child));
                }
            }

            return validTextInputLayouts;
        }

        public boolean isAllValid(Context context) {
            boolean isAllValid = true;
            for (final ValidTextInputLayout inputLayout : validInputLayouts) {
                boolean isMatch = inputLayout.isMatch();
                if (isMatch) {
                    inputLayout.setError(null);
                }
                isAllValid = isMatch && isAllValid;
            }

            if (!isAllValid) {
                Toast.makeText(context, R.string.Validation_error_occurred, Toast.LENGTH_SHORT).show();
            }

            return isAllValid;
        }

        /**
         * Used to clear all field's data.
         */
        public void clearFields() {
            for (final ValidTextInputLayout vtil : validInputLayouts) {
                vtil.setText(null);
            }
        }
    }


}