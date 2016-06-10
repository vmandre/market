package br.com.market.listeners;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import br.com.market.R;

public class DataPickerListener extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "DataPickerListener";
    private Button buttonRetorno;

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.i(TAG, "METHOD: onDateSet");
        buttonRetorno.setText(i2 + "/" + i1 + "/" + i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateDialog");
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void setButtonRetorno(Button buttonRetorno) {
        this.buttonRetorno = buttonRetorno;
    }
}
