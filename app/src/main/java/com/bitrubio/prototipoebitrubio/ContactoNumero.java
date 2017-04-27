package com.bitrubio.prototipoebitrubio;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Orion on 05/05/2016.
 * carga el dialgo con los numero de cada contacto
 */
public class ContactoNumero extends DialogFragment {
    private String numeros[];
    private String idContacto, nombreContacto;
    private String TAG = getClass().getName();
    private CharSequence[] numContacto;
    boolean checked_state[] = {false, false, false};
    Context context;

    public ContactoNumero() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        idContacto = bundle.getString("contactoID");
        nombreContacto = bundle.getString("contactoNom");
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor phoneCursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{idContacto},
                null);

        numeros = new String[phoneCursor.getCount()];
        numContacto = new CharSequence[phoneCursor.getCount()];
        //contactVO.setContactNum(phoneCursor.getCount());
        int i = 0;
        while (phoneCursor.moveToNext()) {
            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            numeros[i] = phoneNumber;
            numContacto[i] = phoneNumber;
            i++;
        }
        phoneCursor.close();
        Resources res = getActivity().getResources();
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        b.setCancelable(false);
        /** Setting a title for the window */
        b.setTitle(Html.fromHtml("<font color='#379aa0'> <small><small>" + nombreContacto + "</small></small></font>"));
        // b.setTitle(nombreContacto);
        b.setMultiChoiceItems(numContacto, null, positiveListener);
        /** Setting a positive button and its listener */
        b.setPositiveButton("Aceptar", positiveClick);
        /** Setting a positive button and its listener */
        AlertDialog d = b.create();
        d.show();
        try {
            int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
            View titleDivider = d.getWindow().getDecorView().findViewById(titleDividerId);
            titleDivider.setBackgroundColor(res.getColor(R.color.colorAccent)); // change divider color

            Button button0 = d.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout.LayoutParams parmetros = new LinearLayout.LayoutParams(
                    170, 170);
            int width = (int) (getActivity().getResources().getDisplayMetrics().widthPixels);
            int widthmedium = 0;
            widthmedium = (width / 2) - 170;
            Log.e(TAG, "width" + width);
            Log.e(TAG, "marginLeft" + widthmedium);
            parmetros.setMargins(widthmedium, 10, 0, 20);
            button0.setLayoutParams(parmetros);

            button0.setText("");
            button0.setBackground(res.getDrawable(R.drawable.aceptar));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    DialogInterface.OnClickListener positiveClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String numeros = "";
            for (int i = 0; i < 3; i++) {
                if (checked_state[i] == true) {
                    numeros = numeros + " " + numContacto[i];

                }
            }

            Guardar(numeros);

            for (int i = 0; i < checked_state.length; i++) {
                if (checked_state[i] == true) {
                    checked_state[i] = false;
                }

            }
        }
    };

    DialogInterface.OnMultiChoiceClickListener positiveListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            checked_state[which] = isChecked;
        }

    };

    private void Guardar(CharSequence selNumeros) {
        Toast.makeText(getActivity(), "Numeros Selecionados" + selNumeros, Toast.LENGTH_LONG).show();


    }

}
