package com.theah64.retrokit.utils;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.theah64.retrokit.R;
import com.theah64.retrokit.widgets.CustomSpinner;

/**
 * Created by theapache64 on 21/12/17.
 */

public class SpinnerValidator {

    private final CustomSpinner[] spinners;

    public SpinnerValidator(CustomSpinner... spinners) {
        this.spinners = spinners;

        //Setting all fields are required
        for (final CustomSpinner spinner : spinners) {
            spinner.setRequired(true);
        }
    }

    public boolean isAllValid(Context context) {
        boolean isAllValid = true;
        for (final CustomSpinner spinner : spinners) {
            if (spinner.getSelectedItemPosition() == 0) {
                isAllValid = false;
                ((TextView) spinner.getSelectedView()).setError(spinner.getErrorMessage());
            }
        }

        if (!isAllValid) {
            SingletonToast.makeText(context, R.string.Validation_error_occurred, Toast.LENGTH_SHORT).show();
        }

        return isAllValid;
    }
}
