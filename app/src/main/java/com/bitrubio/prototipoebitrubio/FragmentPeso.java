package com.bitrubio.prototipoebitrubio;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AsynkData.ServiceHandler;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Entidades.GlobalMetaPeso;
import com.bitrubio.prototipoebitrubio.Metas.MuestraReta;
import com.bitrubio.prototipoebitrubio.Metas.PesoActualMeta;
import com.bitrubio.prototipoebitrubio.Metas.Tab_Bitrubians;
import com.bitrubio.prototipoebitrubio.Metas.TiempoMeta;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by Orion on 09/05/2016.
 */
public class FragmentPeso extends Fragment implements NumberPicker.OnValueChangeListener {

    Typeface tf;
    @Bind(R.id.txt_peso_actual)
    TextView _input_pesoActual;

    @Bind(R.id.txt_resp_peso_actual)
    TextView _resp_peso_actual;

    @Bind(R.id.edit_objetivo)
    TextView _input_objetivo;

    @Bind(R.id.txt_resp_peso_objetivo)
    TextView _resp_peso_objetivo;

    @Bind(R.id.edit_tiempo)
    TextView _input_tiempoMeta;

    @Bind(R.id.txt_resp_tiempo)
    TextView _resp_tiempo;

    @Bind(R.id.edit_retarAmigos)
    TextView _input_retaAmigos;

    @Bind(R.id.txt_armarEquipo)
    TextView _input_armaEquipo;

    @Bind(R.id.edit_privacidad)
    TextView _input_privacidad;

    @Bind(R.id.txt_resp_privacidad)
    TextView _resp_privacidad;

    @Bind(R.id.btn_guardar)
    ImageButton _btn_guardar;

    @Bind(R.id.edit_nomMeta)
    EditText edit_nombreMeta;

    @Bind(R.id.btn_cancel)
    ImageButton _btn_cancelar;

    @Bind(R.id.img_reta)
    TextView imgReta;

    @Bind(R.id.img_apoyo)
    TextView imgApoyo;

    LinearLayout lnr_img_reta;

    FragmentTransaction FT;
    Toolbar toolbar;

    // variables para Crear la meta
    int valuePeso, valueObjetivo, tiempoObjetivo;
    String tipoPeso, paramTiempo;
    StringBuilder stringBuilder;

    StringBuilder stringBuilderEquipo;
    String stNombreMeta;

    GlobalMetaPeso globalMetaPeso;

    ConectaServidor conectaServidor;

    private String success;
    String TAG = getClass().getSimpleName();
    ArrayList<NameValuePair> arrayList;



    public FragmentPeso() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meta_fisica_uno, parentViewGroup, false);
        ButterKnife.bind(this, rootView);

        Log.d(TAG, " Meta Peso ");
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        arrayList = new ArrayList<>();
        globalMetaPeso = GlobalMetaPeso.getInstance();
        lnr_img_reta = (LinearLayout) rootView.findViewById(R.id.lnr_img_reta);
        final Bundle args = new Bundle();

        String nombre = args.getString("nombreMeta","");

        Log.e(TAG,"nombre Meta "+ nombre);
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
            valueObjetivo = Integer.valueOf(globalMetaPeso.getPesoObjetivo());
            tiempoObjetivo = Integer.valueOf(globalMetaPeso.getTiempoMeta());
            stringBuilder = globalMetaPeso.getRetaAmigos();
            stringBuilderEquipo = globalMetaPeso.getEquipoAmigos();
            stNombreMeta = globalMetaPeso.getNombre();

            if (valuePeso > 0) {
                valuePeso = globalMetaPeso.getPesoActual() + 1;
                _resp_peso_actual.setText(valuePeso + " " + tipoPeso);
            }
            if (valueObjetivo > 0) {
                valueObjetivo = globalMetaPeso.getPesoObjetivo() + 1;
                _resp_peso_objetivo.setText(valueObjetivo + " " + tipoPeso);
            }
            if (tiempoObjetivo > 0) {
                tiempoObjetivo = globalMetaPeso.getTiempoMeta() + 1;
                _resp_tiempo.setText(tiempoObjetivo + " " + paramTiempo);
            }
            if (stringBuilder != null) {

                imgReta.setText(stringBuilder.length() + "Personas en tu reta ");

            }
            if (stringBuilderEquipo != null) {
                _input_armaEquipo.setText("Equipo listo! "+stringBuilderEquipo);
            }
            edit_nombreMeta.setText(stNombreMeta);


        }
        stNombreMeta = edit_nombreMeta.getText().toString();
        imgReta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                MuestraReta dialog = new MuestraReta();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
                dialog.show(fm, "titulo");
            }
        });
        imgApoyo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                MuestraReta dialog = new MuestraReta();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
                dialog.show(fm, "titulo");

            }
        });
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
        _input_retaAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final Fragment fragment = new ArmarEquipo();
                final Fragment fragment = new Tab_Bitrubians();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                args.putInt("tipo", 1);
                fragment.setArguments(args);
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _input_armaEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Fragment fragment = new Tab_Bitrubians();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_NONE);
                args.putInt("tipo", 0);
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
                Bundle args = new Bundle();
                Fragment fragmentListMEtas = new FragmentListMetas();
                FT = getFragmentManager().beginTransaction();
                FT.add(R.id.fragment_tipoMetas, fragmentListMEtas);
                args.putInt("tipometas", 1);
                fragmentListMEtas.setArguments(args);
                FT.commit();
            }
        });



        _btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalMetaPeso.setNombre(stNombreMeta);
                // TODO  aqui guardamos la meta // saltamos a la vista con slide
                String valores = "peso actual  " + valuePeso + " peso Objetivo " + valueObjetivo + " escala en " + tipoPeso + " tiempo " + tiempoObjetivo + "Mi reta : " + stringBuilder + " equipo :" + stringBuilderEquipo;
               // Toast.makeText(getContext(), "valores " + valores, Toast.LENGTH_SHORT).show();
                new GeneraMeta(getContext(), arrayList).execute();
            }
        });

        return rootView;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    }

    /**
     * muestra el dialog con la opcion deprivacidad
      */
    public void showPrivacidad() {

        final Dialog d = new Dialog(getActivity(), R.style.DialogTheme);
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
                int intPrivacidad = 0;
                switch (arrayDatos[np.getValue()]) {

                    case "Publico":
                        intPrivacidad = 1;
                        break;
                    case "Personal":
                        intPrivacidad = 2;
                        break;
                    case "Todos":
                        intPrivacidad = 0;
                        break;
                }
                globalMetaPeso.setTipoPrivacidad(intPrivacidad);
                _resp_privacidad.setText(arrayDatos[np.getValue()]); //set the value to textview

                d.dismiss();
            }
        });

        d.show();

    }


    /**
     *
     * @param numberPicker
     * @param color
     * @param tf
     * @param textS
     * @return
     * seteta los elementos de los number pikers
     */
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

    /**
     *
     * @param picker
     * setea el color de la linea dividir en el piker
     */
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

    private void result() {
        if (success.equals("1")) {
            Intent intent = new Intent(getContext(), MetaDetalle.class);
            getContext().startActivity(intent);
            Log.e(TAG, "Meta Creada");
        } else {
            Intent intent = new Intent(getContext(), MetaDetalle.class);
            getContext().startActivity(intent);
            Log.e(TAG, "Error..");
        }
    }

    class GeneraMeta extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        Context context;
        ArrayList<NameValuePair> arrayList;

        public GeneraMeta(Context ctx, ArrayList<NameValuePair> array) {

            this.context = ctx;
            this.arrayList = array;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Guardando Meta");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            ConectaServidor conectaServidor = new ConectaServidor();
            arrayList = new ArrayList<NameValuePair>();
            String servidor = "";
           // String URL = "registro.php";
            //servidor = conectaServidor.getUrl() + URL;
            /*
            ServiceHandler jsonParser = new ServiceHandler();
            String json = jsonParser.makeServiceCall(servidor, ServiceHandler.POST, arrayList);*/
            success = "1";
            /*try {
                JSONObject Obj = new JSONObject(json);
                JSONArray resultArray = Obj.getJSONArray("regMeta");
                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject datosObj = resultArray.getJSONObject(i);
                    //success = datosObj.getString("success");
                    success = "1";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            return success;
        }

        @Override
        protected void onPostExecute(String integer) {
            super.onPostExecute(integer);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                result();
            }
        }
    }
}




