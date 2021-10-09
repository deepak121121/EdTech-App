package com.example.twiniot;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Year;
import java.util.Calendar;


public class calenderFragment extends Fragment {
    TextView text1;
    ImageView button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_calender, container, false);
        text1 = inflate.findViewById(R.id.text);
        button = inflate.findViewById(R.id.cal);
        Calendar c = Calendar.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog Date=new DatePickerDialog(getContext() ,new DatePickerDialog.OnDateSetListener() {
                    /**
                     * @param view       the picker associated with the dialog
                     * @param year       the selected year
                     * @param month      the selected month (0-11 for compatibility with
                     *                   {@link Calendar#MONTH})
                     * @param dayOfMonth the selected day of the month (1-31, depending on
                     */
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        text1.setText(y+"/"+m+"/"+d);

                    }
                },y,m,d);
                Date.show();


            }


        });
        return inflate;
    }

}