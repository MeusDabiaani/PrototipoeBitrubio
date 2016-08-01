package com.bitrubio.prototipoebitrubio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.fitness.request.DeleteAllUserDataRequest;
import com.google.android.gms.vision.text.Line;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mario on 08/01/2016.
 */

public class DatePickerFragment extends android.support.v4.app.DialogFragment {
    DatePickerDialog.OnDateSetListener ondateSet;
    private int day, month, year;
    Calendar calender;
    String TAG = getClass().getSimpleName();

    Context context;

    public DatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {

        ondateSet = ondate;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        day = args.getInt("day");
        month = args.getInt("month");
        year = args.getInt("year");
    }

    @Override
    public void onStart() {
        super.onStart();

        // Title divider
        Resources res = getActivity().getResources();
        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
        final View titleDivider = getDialog().findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(res.getColor(R.color.transparenteBlanco));
        }

        Button pButton = ((DatePickerDialog) getDialog()).getButton(DatePickerDialog.BUTTON_POSITIVE);

        LinearLayout.LayoutParams parmetros = new LinearLayout.LayoutParams(
                170, 170);
        int width = (int) (getActivity().getResources().getDisplayMetrics().widthPixels);
        int widthmedium = 0;
        widthmedium = (width / 2) - 170;
        Log.e(TAG, "width" + width);
        Log.e(TAG, "marginLeft" + widthmedium);
        parmetros.setMargins(widthmedium, 10, 0, 20);
        pButton.setLayoutParams(parmetros);

        pButton.setText("");
        pButton.setBackground(res.getDrawable(R.drawable.aceptar));


    }

    @SuppressLint("NewApi")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calender = Calendar.getInstance();
        calender.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM , yyyy");
        String formattedDate = sdf.format(calender.getTime());

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, ondateSet, year, month, day);
        View view = dialog.getLayoutInflater().inflate(R.layout.titulo_date_piker, null);
        final TextView titulo = (TextView) view.findViewById(R.id.tituloDatePiker);

        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "", dialog);


        DatePicker dpView = dialog.getDatePicker();
        dpView.init(calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        calender.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM , yyyy");
                        String formattedDate = sdf.format(calender.getTime());
                        titulo.setText(formattedDate);
                    }
                });
        titulo.setText(formattedDate);
        dialog.setCustomTitle(view);
        LinearLayout llFirst = (LinearLayout) dpView.getChildAt(0);

        LinearLayout llSecond = (LinearLayout) llFirst.getChildAt(0);


        for (int i = 0; i < llSecond.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) llSecond.getChildAt(i); // Numberpickers in llSecond
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();

            for (Field pf : pickerFields) {

                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);

                    try {
                        pf.set(picker, getResources().getDrawable(R.drawable.bg_divider));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        return dialog;

    }

}