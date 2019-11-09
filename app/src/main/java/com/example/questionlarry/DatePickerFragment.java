package com.example.questionlarry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

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
        /*LoginActivity la = new LoginActivity();
        la.tv_birthDate.setText(getYear() + " " + getMonth() + " " + getDay());*/
        //Toast.makeText(getContext(), getYear(), Toast.LENGTH_SHORT).show();
        return dpd;
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
            MainActivity la = (MainActivity) getActivity();
        }

        /*public void onDateSet(DatePicker view, int year, int month, int day) {

            //la.tv_birthDate.setText(view.getYear() + " " + (view.getMonth()+1) + " " + view.getDayOfMonth());
        }*/
    };

    public String getYear() {
        return String.valueOf(year);
    }

}

/*public void showDatePicker(View v) {
        newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
        //Toast.makeText(this, newFragment.toString(), Toast.LENGTH_SHORT).show();
    }*/
