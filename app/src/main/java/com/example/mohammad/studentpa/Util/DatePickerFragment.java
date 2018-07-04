package com.example.mohammad.studentpa.Util;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {

    String stringDate;
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),
                year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //FIXME: Save Date to textView in Different classes
        stringDate = day + " / " + month + " / " + year;

       /* Intent dateIntent = new Intent(context, TakeReminder.class);
        dateIntent.putExtra("date", stringDate);

        Intent registrationIntent = new Intent(getActivity().getBaseContext(), TakeReminder.class);
        registrationIntent.putExtra("date", stringDate);

        Bundle bundle = new Bundle();
        if (bundle != null) {
            bundle.putString("date", stringDate);
            Milestones milestones = new Milestones();
            milestones.setArguments(bundle);
        }*/

    }

}
