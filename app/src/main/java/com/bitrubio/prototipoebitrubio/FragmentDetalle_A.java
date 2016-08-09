package com.bitrubio.prototipoebitrubio;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.graphics.Paint;
import android.graphics.Typeface;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Metas.AguaMeta;
import com.bitrubio.prototipoebitrubio.Metas.ArmarEquipo;
import com.bitrubio.prototipoebitrubio.Metas.BeberMeta;
import com.bitrubio.prototipoebitrubio.Metas.CelularMeta;
import com.bitrubio.prototipoebitrubio.Metas.FumarMeta;
import com.bitrubio.prototipoebitrubio.Metas.PastelMeta;
import com.bitrubio.prototipoebitrubio.Metas.PesoActualMeta;
import com.bitrubio.prototipoebitrubio.Metas.TiempoMeta;
import com.bitrubio.prototipoebitrubio.Metas.VideoJuegoMeta;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 09/05/2016.
 */
public class FragmentDetalle_A extends Fragment implements NumberPicker.OnValueChangeListener  {
    String TAG = getClass().getSimpleName();
    Typeface tf;
    @Bind(R.id.txt_peso_actual)
    TextView _input_pesoActual;

    @Bind(R.id.edit_objetivo)
    TextView _input_objetivo;

    @Bind(R.id.edit_tiempo)
    TextView _input_tiempoMeta;

    @Bind(R.id.edit_retarAmigos)
    TextView _input_retaAmigos;

    @Bind(R.id.txt_armarEquipo)
    TextView _input_armaEquipo;

    @Bind(R.id.edit_privacidad)
    TextView _input_privacidad;

    @Bind(R.id.btn_guardar)
    ImageButton _btn_guardar;

    @Bind(R.id.btn_cancel)
    ImageButton _btn_cancelar;
    FragmentTransaction FT;
    Toolbar toolbar;

    public FragmentDetalle_A() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meta_fisica_uno, parentViewGroup, false);
        ButterKnife.bind(this, rootView);

        Log.d(TAG,"clase selecionada ");
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");

        _input_pesoActual.setTypeface(tf);
        _input_objetivo.setTypeface(tf);
        _input_tiempoMeta.setTypeface(tf);
        _input_retaAmigos.setTypeface(tf);
        _input_armaEquipo.setTypeface(tf);
        _input_privacidad.setTypeface(tf);


        _input_pesoActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Fragment fragment = new PesoActualMeta();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });
        _input_objetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Fragment fragment = new PesoActualMeta();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();

            }
        });
        _input_tiempoMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Fragment fragment = new TiempoMeta();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _input_armaEquipo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Fragment fragment = new ArmarEquipo();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });
        _input_privacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrivacidad();
            }
        });

        _btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelar Meta", Toast.LENGTH_SHORT).show();
            }
        });

        _btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO  aqui guardamos la meta // saltamos a la vista con slide

                Intent intent = new Intent(getActivity(), MetaDetalle.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }



    public void showPrivacidad() {

        final Dialog d = new Dialog(getActivity(),R.style.DialogTheme);
        d.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.letraVerde1));
        d.setContentView(R.layout.dialog_privacidad);
        ImageButton b1 = (ImageButton) d.findViewById(R.id.button1);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final String[] arrayDatos = {"Publicó", "Personal", "Todos"};
        np.setMaxValue(arrayDatos.length - 1); // max value 100
        np.setMinValue(0);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(arrayDatos);

        setNumberPickerTextColor(np, getResources().getColor(R.color.textColorPrimary), tf, 20);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _input_privacidad.setText(" Privacidad " + arrayDatos[np.getValue()]); //set the value to textview
                d.dismiss();
            }
        });

        d.show();

    }

    // metodo para setar lo valores del piker
    public boolean setNumberPickerTextColor(NumberPicker numberPicker, int color, Typeface tf, int textS) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);

            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    // edita el valor en la casilla selecionada
                    EditText editText = (EditText) child;
                    editText.setTextSize(textS);
                    editText.setTypeface(tf);
                    editText.setTextColor(color);
                    editText.setFocusable(false);
                    //edita el valor de los valores restantes
                    ((EditText) child).setTextColor(color);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTypeface(tf);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTextSize(50);

                    setDividerColor(numberPicker);
                    numberPicker.invalidate();
                    return true;
                } catch (NoSuchFieldException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalAccessException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalArgumentException e) {
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

    private void setDividerColor(NumberPicker picker) {
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
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


}


