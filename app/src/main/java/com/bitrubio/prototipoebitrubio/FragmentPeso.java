package com.bitrubio.prototipoebitrubio;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.AsyncTask;
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

import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Entidades.GlobalMetaPeso;
import com.bitrubio.prototipoebitrubio.Metas.AguaMeta;
import com.bitrubio.prototipoebitrubio.Metas.ArmarEquipo;
import com.bitrubio.prototipoebitrubio.Metas.BeberMeta;
import com.bitrubio.prototipoebitrubio.Metas.CelularMeta;
import com.bitrubio.prototipoebitrubio.Metas.FumarMeta;
import com.bitrubio.prototipoebitrubio.Metas.PastelMeta;
import com.bitrubio.prototipoebitrubio.Metas.PesoActualMeta;
import com.bitrubio.prototipoebitrubio.Metas.Tab_Bitrubians;
import com.bitrubio.prototipoebitrubio.Metas.TiempoMeta;
import com.bitrubio.prototipoebitrubio.Metas.VideoJuegoMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by Orion on 09/05/2016.
 */
public class FragmentPeso extends Fragment implements NumberPicker.OnValueChangeListener  {
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

    // variables para Crear la meta
    int valuePeso,valueObjetivo,tiempoObjetivo;
    String tipoPeso,paramTiempo;
    StringBuilder stringBuilder;
    StringBuilder stringBuilderEquipo;

    GlobalMetaPeso globalMetaPeso ;

    ConectaServidor conectaServidor;
    ProgressDialog progressDialog;
    public FragmentPeso() {

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

         globalMetaPeso = GlobalMetaPeso.getInstance();
        if (globalMetaPeso != null) {

            if (globalMetaPeso.getTipoMeta() == 0) {
                tipoPeso = "kg";
            } else {
                tipoPeso = "lb";
            }

            if (globalMetaPeso.getTipoTiempo() == 0) {
                paramTiempo = "dia(s)";
            } else {
                paramTiempo = "semana(s)";
            }
            valuePeso = Integer.valueOf(globalMetaPeso.getPesoActual());
            valueObjetivo = Integer.valueOf(globalMetaPeso.getPesoObjetivo()) ;
            tiempoObjetivo = Integer.valueOf(globalMetaPeso.getTiempoMeta()) ;
            stringBuilder = globalMetaPeso.getRetaAmigos();
            stringBuilderEquipo = globalMetaPeso.getEquipoAmigos();
            if (valuePeso > 0){
                valuePeso = globalMetaPeso.getPesoActual()+1;
                _input_pesoActual.setText("Peso actual " + valuePeso + " " + tipoPeso);
            }
            if (valueObjetivo > 0 ){
                valueObjetivo = globalMetaPeso.getPesoObjetivo()+1;
                _input_objetivo.setText("Mi objetio " + valueObjetivo + " " + tipoPeso);
            }
            if (tiempoObjetivo > 0 ){
                tiempoObjetivo = globalMetaPeso.getTiempoMeta()+ 1;
                _input_tiempoMeta.setText(" Tiempo para lograrlo " + tiempoObjetivo + " " + paramTiempo);
            }
            if (stringBuilder!=null) {
                _input_retaAmigos.setText("Reta lista! ");
            }
            if (stringBuilderEquipo != null) {
                _input_armaEquipo.setText("Equipo listo!");
            }


        }
     /*   _input_pesoActual.setTypeface(tf);
        _input_objetivo.setTypeface(tf);
        _input_tiempoMeta.setTypeface(tf);
        _input_retaAmigos.setTypeface(tf);
        _input_armaEquipo.setTypeface(tf);
        _input_privacidad.setTypeface(tf);*/
        final Bundle args = new Bundle();

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
                args.putInt("tipo", 2);
                fragment.setArguments(args);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });
        _input_retaAmigos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //final Fragment fragment = new ArmarEquipo();
                final Fragment fragment = new Tab_Bitrubians();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                args.putInt("tipo",1);
                fragment.setArguments(args);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _input_armaEquipo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Fragment fragment = new Tab_Bitrubians();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                args.putInt("tipo",0);
                fragment.setArguments(args);
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
                final Fragment fragment = new FragmentListMetas();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO  aqui guardamos la meta // saltamos a la vista con slide
                String valores = "peso actual  " + valuePeso + " peso Objetivo "+ valueObjetivo + " escala en "+tipoPeso +" tiempo "+tiempoObjetivo+"Mi reta : "+stringBuilder+" equipo :"+ stringBuilderEquipo;
                Toast.makeText(getContext(),"valores "+ valores,Toast.LENGTH_SHORT).show();
                ArrayList<NameValuePair> arrayList = new ArrayList<NameValuePair>();

                new GeneraMeta(getContext(),arrayList).execute();



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
                int intPrivacidad = 0 ;
                switch (arrayDatos[np.getValue()]){

                    case "Publico":
                        intPrivacidad = 1;
                        break;
                    case "Personal":
                        intPrivacidad =  2;
                        break;
                    case "Todos":
                        intPrivacidad = 0;
                        break;
                }
                globalMetaPeso.setTipoPrivacidad(intPrivacidad);
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


