package com.example.appsanri.helper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * * Written by @JoeFachrizal 25/10/2019.
 **/
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private DialogDateListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            mListener = (DialogDateListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener = null;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        // Tahun hari ini
        int year = calendar.get(Calendar.YEAR);

        // Bulan hari ini
        int month = calendar.get(Calendar.MONTH);

        // Tanggal hari ini
        int date = calendar.get(Calendar.DATE);

        return new DatePickerDialog(getActivity(), this, year, month, date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mListener.onDialogDateSet(getTag(), year, month, dayOfMonth);
    }

    public interface DialogDateListener {
        void onDialogDateSet(String tag, int year, int month, int dayOfMonth);
    }

}