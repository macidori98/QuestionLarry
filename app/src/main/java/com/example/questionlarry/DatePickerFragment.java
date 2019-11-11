package com.example.questionlarry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    public int year, month, day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);


        return dpd;
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(getContext(), i + " " + i1 + " " + i2, Toast.LENGTH_SHORT).show();
            MainActivity ma = (MainActivity)getActivity();
            ma.DATE = i + " " + i1 + " " + i2;
        }
    };
}

