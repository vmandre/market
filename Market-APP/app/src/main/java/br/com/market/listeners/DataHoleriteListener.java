package br.com.market.listeners;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class DataHoleriteListener extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "DataHoleriteListener";

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.i(TAG, "METHOD: onDateSet");

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateDialog");
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        Dialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);

        datePicker.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        return  datePicker;
    }
}
