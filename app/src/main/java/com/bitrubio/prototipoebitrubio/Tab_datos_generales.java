package com.bitrubio.prototipoebitrubio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.EditTextAvenir;
import com.bitrubio.prototipoebitrubio.Entidades.DatosPerfilManager;
import com.google.android.gms.vision.text.Line;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 29/02/2016.
 */
public class Tab_datos_generales extends Fragment implements NumberPicker.OnValueChangeListener {
    private String TAG = getClass().getName();
    RelativeLayout lnr_seguro_vida;
    LinearLayout lnr_expand_generales, lnr_expand_fisicos, lnr_expand_seguros, lnr_expand_alergias, lnr_expand_embarazo, lnr_expand_segMedico, lnr_expandOtroSeguro, lnr_extraSeguro;

    LinearLayout lnr_header_generales, lnr_header_fisicos, lnr_header_seguros, lnr_header_alergias, lnr_header_embarazo, lnr_cirugias_previas;
    ValueAnimator mAnimator, mAnimatorFisico, mAnimatorSeguro, mAnimatorAlergias, manimatorEmbarazo, mAnimatorOtroSeguro;

    // botones de cada seccion
    //seccion datos generales
    @Bind(R.id.img_generales)
    ImageView img_generales;
    @Bind(R.id.img_fisicos)
    ImageView img_fisicos;
    @Bind(R.id.img_seguros)
    ImageView img_seguros;
    @Bind(R.id.input_name)
    EditText edit_nombre;
    @Bind(R.id.input_apePat)
    EditText edit_apePat;
    @Bind(R.id.input_apeMat)
    EditText edit_apeMat;
    @Bind(R.id.input_fechaNac)
    EditText edit_fechaNac;
    @Bind(R.id.rad_hombre)
    CheckBox rd_hombre;
    @Bind(R.id.rad_mujer)
    CheckBox rd_mujer;
    @Bind(R.id.input_celular)
    EditText edit_celular;
    @Bind(R.id.input_email)
    EditText edit_mail;
    @Bind(R.id.checkboxSangre)
    CheckBox chk_sangre;
    @Bind(R.id.checkboxOrganos)
    CheckBox chk_organos;
    @Bind(R.id.checkboxNo)
    CheckBox chk_no;
    @Bind(R.id.txt_porcentajeGeneral)
    TextView porcentajeA;
    @Bind(R.id.btn_acepatarG)
    ImageButton img_aceptarG;
    @Bind(R.id.btn_cancelG)
    ImageButton img_cancelarG;
    //seccion datos fisicos
    @Bind(R.id.txt_tipoSanguineo)
    TextView tipoSanguineo;
    LinearLayout lnrTipoSanguineo;
    @Bind(R.id.input_estatura)
    EditText edit_estatura;
    @Bind(R.id.input_peso)
    EditText edit_peso;

    @Bind(R.id.img_preferencia_sexual_opcional)
    ImageView imgPreferenciaOpcional;
    @Bind(R.id.input_vacunas)
    TextView edit_vacunas;
    @Bind(R.id.checkboxHetero)
    CheckBox chk_hetero;
    @Bind(R.id.checkboxHomosexual)
    CheckBox chk_homo;
    @Bind(R.id.checkboxBisexual)
    CheckBox chk_bisex;
    @Bind(R.id.checkboxDiabetes)
    CheckBox chk_diabetes;
    @Bind(R.id.checkboxHipertension)
    CheckBox chk_hipertension;
    @Bind(R.id.checkboxCancer)
    CheckBox chk_cancer;
    @Bind(R.id.checkboxOtroPadecimiento)
    TextView chk_otro;
    @Bind(R.id.input_padecimientosPrev)
    TextView txt_padecimientoPrev;
    @Bind(R.id.img_masPadPrev)
    ImageView img_masPadeciminetos;
    @Bind(R.id.input_lesionesPrev)
    TextView txt_lesionesPrev;
    @Bind(R.id.img_lesionPrev)
    ImageView img_masLesiones;
    @Bind(R.id.input_cirugiasPrev)
    TextView txt_cirugiasPrev;
    @Bind(R.id.img_agregacirugiasPrev)
    ImageView img_masCirugias;
    @Bind(R.id.input_padecimientosActuales)
    TextView txt_padActuales;
    @Bind(R.id.img_padAct)
    ImageView img_masPadActuales;
    @Bind(R.id.toggle_alergias)
    ToggleButton toggle_alergias;
    @Bind(R.id.edit_sustancias_act)
    EditText edit_sustancias;
    @Bind(R.id.edit_otras_alergias)
    EditText edit_otrosAlergias;
    @Bind(R.id.toggle_emabarazada)
    ToggleButton toggle_embarazada;
    @Bind(R.id.input_semanas)
    EditText edit_semanas;
    @Bind(R.id.toggle_ejercicio)
    ToggleButton toggle_ejercicio;
    @Bind(R.id.toggle_refresco)
    ToggleButton toggle_refesco;
    @Bind(R.id.toggle_fuma)
    ToggleButton toggle_fuma;
    @Bind(R.id.toggle_alcohol)
    ToggleButton toggle_bebidas;
    @Bind(R.id.toggle_drogas)
    ToggleButton toggle_drogas;
    @Bind(R.id.txt_porcentajeFisico)
    TextView txt_porcentajeB;
    @Bind(R.id.btn_acepatarF)
    ImageButton img_aceptarF;
    @Bind(R.id.btn_cancelF)
    ImageButton img_cancelarF;
    LinearLayout lnr_edit_peso;
    //seccion datos seguros
    @Bind(R.id.num_imss)
    EditText edit_numIMSS;
    @Bind(R.id.num_isste)
    EditText edit_numISSTE;
    @Bind(R.id.num_seg_popular)
    EditText edit_numPopular;
    @Bind(R.id.num_seg_pemex)
    EditText edit_numPemex;
    @Bind(R.id.txt_otroSeguro)
    TextView txt_otroSeguro;
    @Bind(R.id.img_otroSeguro)
    ImageView img_otroSeguro;
    @Bind(R.id.txt_seguroVida)
    TextView txt_otroSeguroVida;
    @Bind(R.id.img_otroSeguroNormal)
    ImageView img_otroSeguroVida;
    @Bind(R.id.edit_companiaVida)
    EditText edit_companiaVida;
    @Bind(R.id.edit_polizaVida)
    EditText edit_polizaVida;
    @Bind(R.id.edit_vigenciaVida)
    EditText edit_vigenciaVida;
    @Bind(R.id.txt_porcentajeSeguros)
    TextView txt_porcentajeC;
    @Bind(R.id.btn_acepatarS)
    ImageButton img_aceptarS;
    @Bind(R.id.btn_cancelS)
    ImageButton img_cancelS;

    int count, count_otro, count_pad_familiar;
    private LinearLayout mLayoutPadecimietosPrev, mLayoutPadAct;
    private EditText editPrev;
    private EditText editAct;
    private EditText editTextLesiones;
    private EditText editSeguros;
    StringBuilder arrayPadecimientos, arrayPadecimientosPrev;

    private List<EditText> editTextPrev = new ArrayList<EditText>();
    private List<EditText> editTextAct = new ArrayList<EditText>();
    private List<EditText> arrayEditText = new ArrayList<EditText>();
    private List<EditText> arrayListLesiones = new ArrayList<EditText>();
    private List<EditText> arrayListCirugias = new ArrayList<EditText>();
    private List<EditText> arrayListSeguros = new ArrayList<EditText>();

    DatosPerfilManager perfilManager;
    String nombre, paterno, materno;
    String radio_sexualidad, fecha_nac, celular, email, password, sangre, organos, donar;

    ViewGroup layout;
    int contador1 = 0;
    int con1, con2, con3, con4, con5, con6, con7, con8;
    int valor;
    int valSeg;
    Toast toast;

    private StringBuilder stringPadPrev, stringPadAct;
    // string parte 2 Datos Fisicos
    String tipo_sanguineo, estatura, peso, sexualidad, diabetes, hipertension, cancer, otro_ant, alergias, sustancias, ambientes, embarazada, semanas_emb, ejercicio, fuma, alcohol, drogas;
    String imss, isste, popular, compania, poliza, vigencia, conpaniaVida, polizaVida, vigenciaVida;
    Typeface tf;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_datos_generales, container, false);
        ButterKnife.bind(this, v);
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-light.ttf");
        layout = (ViewGroup) v.findViewById(R.id.completa_formulario);
        lnr_edit_peso = (LinearLayout) v.findViewById(R.id.lnr_edite_peso);
        perfilManager = new DatosPerfilManager(getContext());
        controlesDesplegables(v);
        toggleDatosFisicos(v);
        checkBoxDatosFisicos(v);
        inicializarCampos(v);
        agregarCampos(v);
        leeArchivoPerfil(v);

        guardaDatos();
        return v;
    }

    private void guardaDatos() {
        // guarda los datos generales
        img_aceptarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edit_nombre.getText().toString();
                paterno = edit_apePat.getText().toString();
                materno = edit_apeMat.getText().toString();
                fecha_nac = edit_fechaNac.getText().toString();
                celular = edit_celular.getText().toString();
                email = edit_mail.getText().toString();
                perfilManager.insertDatosA(nombre, paterno, materno, radio_sexualidad, fecha_nac,
                        celular, email, password, donar, sangre, organos);
                colapsar(lnr_expand_generales);
                Toast.makeText(getContext(), "Datos generales guardados", Toast.LENGTH_LONG).show();

            }
        });

        img_aceptarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recupero los valores de los padecimientos previos
                stringPadAct = new StringBuilder();
                for (EditText mieditAct : editTextPrev) {
                    arrayPadecimientos = stringPadAct.append(mieditAct.getText().toString() + ",");
                }
                stringPadPrev = new StringBuilder();
                for (EditText mieditOtros : editTextAct) {
                    arrayPadecimientosPrev = stringPadPrev.append(mieditOtros.getText().toString() + ",");
                }
                //   String tipoSanguineo = spinnerTipoSangreListener(v);
                estatura = edit_estatura.getText().toString();
                peso = edit_peso.getText().toString();

                String vacunas = "viruela";
                String cirugias = "cirugias";

                perfilManager.insertDatosB(tipo_sanguineo, estatura, peso, sexualidad, vacunas, diabetes,
                        hipertension, cancer, otro_ant, String.valueOf(arrayPadecimientos), cirugias,
                        String.valueOf(arrayPadecimientosPrev), embarazada, semanas_emb, alergias, sustancias, ambientes, ejercicio, fuma, alcohol, drogas);
                colapsar(lnr_expand_fisicos);
                Toast.makeText(getContext(), "Datos fisicos guardados", Toast.LENGTH_LONG).show();
            }
        });
        img_aceptarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imss = edit_numIMSS.getText().toString();
                isste = edit_numISSTE.getText().toString();
                popular = edit_numPopular.getText().toString();
                String seguro_medico;
                if (imss != null || isste != null || popular != null) {
                    seguro_medico = "1";
                } else {
                    seguro_medico = "0";
                }
                compania = edit_companiaVida.getText().toString();
                poliza = edit_polizaVida.getText().toString();
                vigencia = edit_vigenciaVida.getText().toString();
                String otro_seguro_medico;
                if (compania != null || poliza != null || vigencia != null) {
                    otro_seguro_medico = "1";
                } else {
                    otro_seguro_medico = "0";
                }
                conpaniaVida = edit_companiaVida.getText().toString();
                polizaVida = edit_polizaVida.getText().toString();
                vigenciaVida = edit_vigenciaVida.getText().toString();
                String seguro_vida;
                if (conpaniaVida != null || polizaVida != null || vigenciaVida != null) {
                    seguro_vida = "1";
                } else {
                    seguro_vida = "0";
                }
                Log.e(TAG, "imss" + imss);
                perfilManager.insertDatosC(seguro_medico, imss, isste, popular, otro_seguro_medico, compania, poliza, vigencia, seguro_vida, conpaniaVida, polizaVida, vigenciaVida);
                Toast.makeText(getContext(), "Datos seguros guardados", Toast.LENGTH_LONG).show();
                colapsar(lnr_expand_seguros);
            }
        });

        img_cancelarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_generales.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                colapsar(lnr_expand_generales);
            }
        });
        img_cancelarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_fisicos.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                colapsar(lnr_expand_fisicos);
            }
        });
        img_cancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_seguros.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                colapsar(lnr_expand_seguros);
            }
        });
    }

    //region Inicializa los campos
    private void inicializarCampos(View v) {
        hideKeyboard(layout);
        rd_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    radio_sexualidad = "H";
                    rd_mujer.setChecked(false);

                    hideKeyboard(view);
                } else {
                    // Toast.makeText(getBaseContext(), "No donar Sangre", Toast.LENGTH_LONG).show();
                    radio_sexualidad = "M";
                    rd_hombre.setChecked(true);
                }
            }
        });
        rd_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();

                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    radio_sexualidad = "M";
                    rd_hombre.setChecked(false);
                    hideKeyboard(view);
                } else {
                    // Toast.makeText(getBaseContext(), "No donar Sangre", Toast.LENGTH_LONG).show();
                    radio_sexualidad = "H";
                    rd_mujer.setChecked(true);
                }
            }
        });

        edit_fechaNac.setSingleLine(true);
        edit_fechaNac.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showDatePicker();
                    hideKeyboard(layout);
                    return true;
                }
                return false;
            }
        });
        lnrTipoSanguineo = (LinearLayout) v.findViewById(R.id.lnr_tipoSanguineo);
        lnrTipoSanguineo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] array = {"AB", "AB-", "A+", "A-", "B+", "B-", "O+", "O-"};
                showMyPiker(R.layout.dialog_tipo_sanguineo, tipoSanguineo, "", 0, array);
            }
        });
        LinearLayout lnr_estatura = (LinearLayout) v.findViewById(R.id.lnr_estatura);
        lnr_estatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] array = {"cm", "ft"};
                String title = "Estatura";
                showDialog(title, R.layout.dialog_number_piker, edit_estatura, "", 250, 0, 165, array);

            }
        });


        lnr_edit_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] array = {"kg", "lb"};
                String title = "Peso";
                showDialog(title, R.layout.dialog_number_piker, edit_peso, "", 150, 0, 75, array);

            }
        });
        edit_vacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
                DialogVacunas dialogVacunas = new DialogVacunas();
                dialogVacunas.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
                dialogVacunas.show(manager, "Vacunas");

            }
        });

        imgPreferenciaOpcional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DialogOpcional dialogVacunas = new DialogOpcional();
                dialogVacunas.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
                dialogVacunas.show(fm, "Opcional");
            }
        });
    }

    private void showDialog(String title, int dialog_layout, final TextView textViewSalida, final String stringSalida,
                            int maxValue, int minValue, int mediumValue, final String[] arreglo) {

        final Dialog d = new Dialog(getActivity(), R.style.DialogTheme);
        d.setContentView(dialog_layout);
        TextView textView = (TextView) d.findViewById(R.id.title);
        textView.setText(title);
        ImageButton b1 = (ImageButton) d.findViewById(R.id.button1);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);

        np.setMaxValue(maxValue); // max value 100
        np.setMinValue(minValue);   // min value 0
        np.setValue(mediumValue);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        setDividerColor(np);
        setNumberPickerTextColor(np, getResources().getColor(R.color.textColorPrimary), tf, 25);

        final NumberPicker pikM = (NumberPicker) d.findViewById(R.id.numberPicker2);
        final String[] arrayDatos = arreglo;
        pikM.setMinValue(minValue);
        pikM.setMaxValue(arrayDatos.length - 1);
        pikM.setWrapSelectorWheel(false);
        pikM.setDisplayedValues(arrayDatos);
        setDividerColor(pikM);
        setNumberPickerTextColor(pikM, getResources().getColor(R.color.textColorPrimary), tf, 25);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSalida.setText(stringSalida + " " + String.valueOf(np.getValue()) + " " + arrayDatos[pikM.getValue()]); //set the value to textview
                d.dismiss();
            }
        });
        d.show();
    }

    //endregion
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    }

    private void showMyPiker(int dialog_layout, final TextView textViewSalida, final String stringSalida, int minValue, final String[] arreglo) {
        final Dialog d = new Dialog(getActivity(), R.style.DialogTheme);
        d.setContentView(dialog_layout);
        ImageButton b1 = (ImageButton) d.findViewById(R.id.button1);
        final NumberPicker pikM = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final String[] arrayDatos = arreglo;
        pikM.setMinValue(minValue);
        pikM.setMaxValue(arrayDatos.length - 1);
        pikM.setValue(3);
        pikM.setWrapSelectorWheel(false);
        pikM.setDisplayedValues(arrayDatos);
        setDividerColor(pikM);
        setNumberPickerTextColor(pikM, getResources().getColor(R.color.textColorPrimary), tf, 18);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSalida.setText(stringSalida + " " + String.valueOf(arrayDatos[pikM.getValue()])); //set the value to textview
                d.dismiss();
            }
        });
        d.show();
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color, Typeface tf, int textS) {
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
                    editText.setFocusable(false);
                    editText.setTextColor(color);
                    //edita el valor de los valores restantes
                    ((EditText) child).setTextColor(color);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTypeface(tf);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTextSize(50);
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

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    //region Dialog-Calendario
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String mes = null;
            if (monthOfYear + 1 == 1) {
                mes = "Ene";
            }
            if (monthOfYear + 1 == 2) {
                mes = "Feb";
            }
            if (monthOfYear + 1 == 3) {
                mes = "Mar";
            }
            if (monthOfYear + 1 == 4) {
                mes = "Abr";
            }
            if (monthOfYear + 1 == 5) {
                mes = "May";
            }
            if (monthOfYear + 1 == 6) {
                mes = "Jun";
            }
            if (monthOfYear + 1 == 7) {
                mes = "Jul";
            }
            if (monthOfYear + 1 == 8) {
                mes = "Ago";
            }
            if (monthOfYear + 1 == 9) {
                mes = "Sep";
            }
            if (monthOfYear + 1 == 10) {
                mes = "Oct";
            }
            if (monthOfYear + 1 == 11) {
                mes = "Nov";
            }
            if (monthOfYear + 1 == 12) {
                mes = "Dic";
            }
            edit_fechaNac.setText(String.valueOf(dayOfMonth) + "/" + mes + "/" + String.valueOf(year));
        }
    };

    //endregion
    //recueperamos informacion
    private void leeArchivoPerfil(View v) {
        HashMap<String, String> datos = perfilManager.getDatosPerfil();
        //region GET DATOS GENERALES
        String nombre_user = datos.get(perfilManager.NOMBRE);
        String apepat_user = datos.get(perfilManager.FIRST_APE);
        String apemat_user = datos.get(perfilManager.SECOND_APE);
        String sexualidad_user = datos.get(perfilManager.SEXO);
        String fecha_nac = datos.get(perfilManager.FECHA_NAC);
        String cel = datos.get(perfilManager.CELULAR);
        String mail = datos.get(perfilManager.MAIL);
        String donaciones = "";
        donaciones = datos.get(perfilManager.DONACIONES);
        String organo = "";
        organo = datos.get(perfilManager.ORGANOS);
        String sangre = "";
        sangre = datos.get(perfilManager.SANGRE);
        if (nombre_user != null) {
            con1 = 1;
        }
        if (apepat_user != null) {
            con2 = 1;
        }
        if (apemat_user != null) {
            con3 = 1;
        }
        if (sexualidad_user != null) {
            con4 = 1;
        }
        if (fecha_nac != null) {
            con5 = 1;
        }
        if (cel != null) {
            con6 = 1;
        }
        if (mail != null) {
            con7 = 1;
        }
        if (donaciones != null || organo != null || sangre != null) {
            con8 = 1;
        }
        contador1 = con1 + con2 + con3 + con4 + con5 + con6 + con7 + con8;
        int porcentajeG = contador1 * 100 / 8;

        porcentajeA.setText("" + String.valueOf(porcentajeG) + " %");
        //endregion
        //region SET DATOS GERNERALES
        edit_nombre.setText(nombre_user);
        edit_apePat.setText(apepat_user);
        edit_apeMat.setText(apemat_user);
        if (sexualidad_user != (null)) {
            if (sexualidad_user.equals("H")) {
                rd_hombre.setChecked(true);
                rd_mujer.setChecked(false);
                lnr_header_embarazo.setVisibility(View.GONE);
                lnr_expand_embarazo.setVisibility(View.GONE);
            } else {
                rd_mujer.setChecked(true);
                rd_hombre.setChecked(false);
                lnr_header_embarazo.setVisibility(View.VISIBLE);
                lnr_expand_embarazo.setVisibility(View.VISIBLE);
            }
        }
        edit_fechaNac.setText(fecha_nac);
        edit_celular.setText(cel);
        edit_mail.setText(mail);
        if (donaciones != null) {
            if (donaciones.equals("si")) {
                chk_no.setChecked(false);
            } else {
                chk_no.setChecked(true);
            }
        }
        if (sangre != null) {
            if (sangre.equals("si")) {
                chk_sangre.setChecked(true);
            } else {
                chk_sangre.setChecked(false);
            }
        }
        if (organo != null) {
            if (organo.equals("si")) {
                chk_organos.setChecked(true);
            } else {
                chk_organos.setChecked(false);
            }
        }
        //endregion

        String tipoSangre = datos.get(perfilManager.TIPO_SANGUINEO);
        String estatura = datos.get(perfilManager.ESTATURA);
        String peso = datos.get(perfilManager.PESO);
        String preferenciasex = datos.get(perfilManager.PREFERENCIA_SEX);
        String vacunas = datos.get(perfilManager.VACUNAS);
        String diabetes = datos.get(perfilManager.DIABETES);
        String hipertencion = datos.get(perfilManager.HIPERTENCION);
        String cancer = datos.get(perfilManager.CANCER);
        String otroAnt = datos.get(perfilManager.OTRO_ANT);
        final String padecimientoPrev = datos.get(perfilManager.PADECIMINETOS);
        final String padecimientosAct = datos.get(perfilManager.PADECIMIENTOS_ACT);
        String embarazada = "";
        embarazada = datos.get(perfilManager.EMBARAZADA);
        String semanas_emb = "";
        semanas_emb = datos.get(perfilManager.SEMANAS_EMB);
        String alergias = "";
        alergias = datos.get(perfilManager.ALERGIAS);
        String sustancias = "";
        sustancias = datos.get(perfilManager.SUSTANCIAS);
        String ambientales = "";
        ambientales = datos.get(perfilManager.AMBIENTALES);
        String ejercicio = "";
        ejercicio = datos.get(perfilManager.EJERCICIO);
        String fuma = "";
        fuma = datos.get(perfilManager.TABACO);
        String alcohol = "";
        alcohol = datos.get(perfilManager.ALCOHOL);
        String drogas = "";
        drogas = datos.get(perfilManager.DROGAS);

        valor = 0;
        if (tipoSangre != null) {
            valor = 1;
        }
        if (estatura != null) {
            valor += 1;
        }
        if (peso != null) {
            valor += 1;
        }
        if (preferenciasex != null) {
            valor += 1;
        }
        if (vacunas != null) {
            valor += 1;
        }
        if (diabetes != null || hipertencion != null || cancer != null || otroAnt != null) {
            valor += 1;
        }
        if (padecimientoPrev != null) {
            valor += 1;
        }
        if (padecimientosAct != null) {
            valor += 1;
        }
        if (alergias != null || sustancias != null || ambientales != null) {
            valor += 1;
        }
        if (embarazada != null || semanas_emb != null) {
            valor += 1;
        }
        if (ejercicio != null) {
            valor += 1;
        }
        if (fuma != null) {
            valor += 1;
        }
        if (alcohol != null) {
            valor += 1;
        }
        if (drogas != null) {
            valor += 1;
        }
        edit_estatura.setText(estatura);
        edit_peso.setText(peso);
        if (preferenciasex != null) {
            switch (preferenciasex) {
                case "1":
                    chk_hetero.setChecked(true);
                    break;
                case "2":
                    chk_homo.setChecked(true);
                    break;
                case "3":
                    chk_bisex.setChecked(true);
                    break;
                default:
                    chk_hetero.setChecked(true);
            }
        }
        if (diabetes == "1" || diabetes != null) {
            chk_diabetes.setChecked(true);
        }
        if (hipertencion == "1" || hipertencion != null) {
            chk_hipertension.setChecked(true);
        }
        if (cancer == "1" || cancer != null) {
            chk_cancer.setChecked(true);
        }
        if (alergias != null) {
            if (alergias.equals("1")) {
                toggle_alergias.setChecked(true);
                lnr_expand_alergias.setActivated(true);
            }
        }
        if (embarazada != null) {
            if (embarazada.equals("1")) {
                toggle_embarazada.setChecked(true);
                expandir(lnr_expand_embarazo);
            }
        }
        if (ejercicio != null) {
            if (ejercicio.equals("1")) {
                toggle_ejercicio.setChecked(true);
            }
        }
        if (fuma != null) {
            if (fuma.equals("1")) {
                toggle_fuma.setChecked(true);
            }
        }
        if (alcohol != null) {
            if (alcohol.equals("1")) {
                toggle_bebidas.setChecked(true);
            }
        }
        if (drogas != null) {
            if (drogas.equals("1")) {
                toggle_drogas.setChecked(true);
            }
        }
        if (padecimientosAct != null) {
            final String[] arrayPadAct = padecimientosAct.split(",");
            final ArrayList<String> ArrayListAct = new ArrayList<String>();
            final int valueA = 200;
            mLayoutPadAct = (LinearLayout) v.findViewById(R.id.lnr_agregaPadecimientoAct);
            mLayoutPadAct.setOrientation(LinearLayout.VERTICAL);
            int i;
            for (i = 0; i < arrayPadAct.length; i++) {
                ArrayListAct.add(arrayPadAct[i]);
                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), valueA + 1, "", arrayPadAct[i], 14, editTextAct);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                final int finalI = i;
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            ArrayListAct.remove(finalI);
                            StringBuilder temp = null;
                            stringPadPrev = new StringBuilder();
                            for (int r = 0; r < ArrayListAct.size(); r++) {
                                temp = stringPadAct.append(ArrayListAct.get(r));
                            }
                            perfilManager.editPadecimientoActual(String.valueOf(temp));
                            mLayoutPadecimietosPrev.removeViewAt(0);
                            mLayoutPadecimietosPrev.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                mLayoutPadecimietosPrev.addView(myLinearLayout);
                mLayoutPadecimietosPrev.addView(myView);
                count++;
            }
        }
        if (padecimientoPrev != null) {
            final String[] arrayPadPrev = padecimientoPrev.split(",");
            final ArrayList<String> ArrayListPrev = new ArrayList<String>();
            int valueB = 400;
            mLayoutPadecimietosPrev = (LinearLayout) v.findViewById(R.id.lnr_agregaPadecimientoPrev);
            mLayoutPadecimietosPrev.setOrientation(LinearLayout.VERTICAL);
            count = 1;
            for (int i = 0; i < arrayPadPrev.length; i++) {
                //System.out.println(arrayPadPrev[i]);
                ArrayListPrev.add(arrayPadPrev[i]);
                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), valueB + 1, "", arrayPadPrev[i], 14, editTextPrev);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                final int finalI = i;
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (v.getId() == myImageView.getId()) {
                            ArrayListPrev.remove(finalI);
                            StringBuilder temp = null;
                            stringPadPrev = new StringBuilder();
                            for (int r = 0; r < ArrayListPrev.size(); r++) {
                                temp = stringPadPrev.append(ArrayListPrev.get(r));
                            }
                            perfilManager.editPadecimientoPrev(String.valueOf(temp));

                            mLayoutPadecimietosPrev.removeViewAt(0);
                            mLayoutPadecimietosPrev.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                mLayoutPadecimietosPrev.addView(myLinearLayout);
                mLayoutPadecimietosPrev.addView(myView);
                count++;
            }

        }
        int porcentajeB = valor * 100 / 14;
        txt_porcentajeB.setText("" + String.valueOf(porcentajeB) + " %");
        String seguroMedico = datos.get(perfilManager.SEGURO_MEDICO);
        String numImss = datos.get(perfilManager.NUM_IMSS);
        String numIsste = datos.get(perfilManager.NUM_ISSTE);
        String numPopular = datos.get(perfilManager.NUM_POPULAR);
        String otroSeg = datos.get(perfilManager.OTRO_SEGURO);
        String compañia = datos.get(perfilManager.COMPANIA);
        String poliza = datos.get(perfilManager.POLIZA);
        String vigencia = datos.get(perfilManager.VIGENCIA);
        String seguroVida = datos.get(perfilManager.SEGURO_VIDA);
        String companiaVida = datos.get(perfilManager.COMPANIA_VIDA);
        String polizaVida = datos.get(perfilManager.POLIZA_VIDA);
        String vigenciaVida = datos.get(perfilManager.VIGENCIA_VIDA);
        valSeg = 0;
        if (seguroMedico != null) {
            valSeg = 1;
        }
        if (numImss != null) {
            valSeg += 1;
        }
        if (numIsste != null) {
            valSeg += 1;
        }
        if (numPopular != null) {
            valSeg += 1;
        }
        if (otroSeg != null) {
            valSeg += 1;
        }
        if (compañia != null) {
            valSeg += 1;
        }
        if (poliza != null) {
            valSeg += 1;
        }
        if (vigencia != null) {
            valSeg += 1;
        }
        if (seguroVida != null) {
            valSeg += 1;
        }
        if (companiaVida != null) {
            valSeg += 1;
        }
        if (polizaVida != null) {
            valSeg += 1;
        }
        if (vigenciaVida != null) {
            valSeg += 1;
        }
        edit_numIMSS.setText(numImss);
        edit_numISSTE.setText(numIsste);
        edit_numPopular.setText(numPopular);
        edit_companiaVida.setText(otroSeg);
        edit_polizaVida.setText(poliza);
        edit_vigenciaVida.setText(vigencia);
        edit_companiaVida.setText(companiaVida);
        edit_polizaVida.setText(polizaVida);
        edit_vigenciaVida.setText(vigenciaVida);
        int porcentajeC = valSeg * 100 / 12;
        txt_porcentajeC.setText("" + String.valueOf(porcentajeC) + " %");
    }

    private void agregarCampos(final View v) {

        mLayoutPadecimietosPrev = (LinearLayout) v.findViewById(R.id.lnr_agregaPadecimientoPrev);
        mLayoutPadecimietosPrev.setOrientation(LinearLayout.VERTICAL);
        count = 1;
        //editTextPrev
        img_masPadeciminetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Padecimiento previo", "", 14, editTextPrev);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            mLayoutPadecimietosPrev.removeViewAt(0);
                            mLayoutPadecimietosPrev.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                mLayoutPadecimietosPrev.addView(myLinearLayout);
                mLayoutPadecimietosPrev.addView(myView);

                expandir(lnr_expand_fisicos);
                count++;
            }
        });


        mLayoutPadAct = (LinearLayout) v.findViewById(R.id.lnr_agregaPadecimientoAct);
        mLayoutPadAct.setOrientation(LinearLayout.VERTICAL);

        count_otro = 100;
        //editTextAct
        img_masPadActuales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Padecimiento actual", "", 14, editTextAct);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            mLayoutPadAct.removeViewAt(0);
                            mLayoutPadAct.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                mLayoutPadAct.addView(myLinearLayout);
                mLayoutPadAct.addView(myView);

                layout.requestLayout();
                //expandir(lnr_expand_fisicos);

                count_otro++;
            }
        });

        count_pad_familiar = 200;
        final ViewGroup lnr_padFamiliares = (ViewGroup) v.findViewById(R.id.lnr_padFamiliar);
        chk_otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count_pad_familiar);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count_pad_familiar, "Antecedente familiar", "", 14, arrayEditText);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            lnr_padFamiliares.removeViewAt(0);
                            lnr_padFamiliares.removeViewAt(0);
                        }
                    }
                };
                myImageView.setOnClickListener(listener);
                lnr_padFamiliares.addView(myLinearLayout);
                lnr_padFamiliares.addView(myView);
                count_pad_familiar++;

               // expandir(lnr_expand_fisicos);
                // expandir(lnr_expand_fisicos); // ok
            }

        });

        count = 300;
        final LinearLayout lnrLesiones = (LinearLayout) v.findViewById(R.id.lnr_agregaLesionPrev);

        img_masLesiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Lesion previa", "", 14, arrayListLesiones);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            lnrLesiones.removeViewAt(0);
                            lnrLesiones.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                lnrLesiones.addView(myLinearLayout);
                lnrLesiones.addView(myView);

                count++;
                expandir(lnr_expand_fisicos);
            }
        });

        lnr_cirugias_previas = (LinearLayout) v.findViewById(R.id.lnr_agregaCirugiaPrev);
        count = 400;
        img_masCirugias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Otra cirugia", "", 14, arrayListCirugias);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            lnr_cirugias_previas.removeViewAt(0);
                            lnr_cirugias_previas.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);
                lnr_cirugias_previas.addView(myLinearLayout);
                lnr_cirugias_previas.addView(myView);

                count++;
                expandir(lnr_expand_fisicos);

            }
        });

        lnr_extraSeguro = (LinearLayout) v.findViewById(R.id.lnr_extraSeguro);
        count = 500;
        img_otroSeguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);

                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();
                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Compañia", "", 14, arrayListSeguros);
                EditText myEditText = newElemento.myEdit();
                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            lnr_extraSeguro.removeViewAt(0);
                            lnr_extraSeguro.removeViewAt(0);
                            lnr_extraSeguro.removeViewAt(0);
                            lnr_extraSeguro.removeViewAt(0);
                        }
                    }
                };

                myImageView.setOnClickListener(listener);

                AgregaLinearLayout newLinear2 = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout2 = newLinear2.CreaLayoutHorizontal();
                AgregaCampo newElemento2 = new AgregaCampo(getActivity(), count + 50, "Numero de seguro", "", 14, arrayListSeguros);
                EditText myEditText2 = newElemento2.myEdit();
                View myView2 = newElemento2.setMyView();
                myLinearLayout2.addView(myEditText2);

                lnr_extraSeguro.addView(myLinearLayout);
                lnr_extraSeguro.addView(myView);
                lnr_extraSeguro.addView(myLinearLayout2);
                lnr_extraSeguro.addView(myView2);
                count++;
                expandir(lnr_expand_fisicos);
            }
        });

        lnr_seguro_vida = (RelativeLayout) v.findViewById(R.id.lnr_segurosVida);
        final LinearLayout lnrContenido = (LinearLayout) v.findViewById(R.id.contenidoExtraSeguro);

        count = 700;
        img_otroSeguroVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AgregaLinearLayout newLinear = new AgregaLinearLayout(getActivity(), count);

                LinearLayout myLinearLayout = newLinear.CreaLayoutHorizontal();

                AgregaCampo newElemento = new AgregaCampo(getActivity(), count, "Compañia", "", 14, arrayListSeguros);
                EditText myEditText = newElemento.myEdit();

                final ImageView myImageView = newElemento.setMyImageView();
                View myView = newElemento.setMyView();
                myLinearLayout.addView(myEditText);
                myLinearLayout.addView(myImageView);

                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == myImageView.getId()) {
                            lnrContenido.removeViewAt(0);
                            lnrContenido.removeViewAt(0);
                            lnrContenido.removeViewAt(0);
                            lnrContenido.removeViewAt(0);
                            lnrContenido.removeViewAt(0);
                            lnrContenido.removeViewAt(0);
                        }
                    }
                };
                myImageView.setOnClickListener(listener);

                AgregaLinearLayout newLinear2 = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout2 = newLinear2.CreaLayoutHorizontal();
                AgregaCampo newElemento2 = new AgregaCampo(getActivity(), count + 50, "Poliza", "", 14, arrayListSeguros);
                EditText myEditText2 = newElemento2.myEdit();
                View myView2 = newElemento2.setMyView();
                myLinearLayout2.addView(myEditText2);


                AgregaLinearLayout newLinear3 = new AgregaLinearLayout(getActivity(), count);
                LinearLayout myLinearLayout3 = newLinear3.CreaLayoutHorizontal();
                AgregaCampo newElemento3 = new AgregaCampo(getActivity(), count + 50, "Vigencia", "", 14, arrayListSeguros);
                EditText myEditText3 = newElemento3.myEdit();
                View myView3 = newElemento3.setMyView();
                myLinearLayout3.addView(myEditText3);

                lnrContenido.addView(myLinearLayout);
                lnrContenido.addView(myView);
                lnrContenido.addView(myLinearLayout2);
                lnrContenido.addView(myView2);
                lnrContenido.addView(myLinearLayout3);
                lnrContenido.addView(myView3);


                count++;
                expandir(lnr_expand_seguros);

            }
        });
    }

    private void toggleDatosFisicos(final View v) {
        toggle_alergias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alergias = "1";
                    expandir(lnr_expand_alergias);
                } else {
                    alergias = "0";
                    colapsar(lnr_expand_alergias);
                }
            }
        });
        toggle_embarazada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    embarazada = "1";
                    expandir(lnr_expand_embarazo);
                } else {
                    embarazada = "0";
                    colapsar(lnr_expand_embarazo);
                }
            }
        });
        toggle_ejercicio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ejercicio = "1";
                } else {
                    ejercicio = "0";
                }
            }
        });
        toggle_fuma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fuma = "1";
                } else {
                    fuma = "0";
                }
            }
        });
        toggle_bebidas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alcohol = "1";
                } else {
                    alcohol = "0";
                }
            }
        });
        toggle_drogas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drogas = "1";
                } else {
                    drogas = "0";
                }
            }
        });
    }

    private void checkBoxDatosFisicos(View v) {

        chk_hetero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sexualidad = "1";
                    chk_homo.setChecked(false);
                    chk_bisex.setChecked(false);
                    hideKeyboard(layout);
                }
            }
        });
        chk_homo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sexualidad = "2";
                    chk_hetero.setChecked(false);
                    chk_bisex.setChecked(false);
                    hideKeyboard(layout);
                }
            }
        });
        chk_bisex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sexualidad = "3";
                    chk_hetero.setChecked(false);
                    chk_homo.setChecked(false);
                    hideKeyboard(layout);
                }
            }
        });

        chk_diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    diabetes = "1";
                    hideKeyboard(layout);
                }
            }
        });

        chk_hipertension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    hipertension = "1";
                    hideKeyboard(layout);
                }
            }
        });
        chk_cancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    cancer = "1";
                    hideKeyboard(layout);
                }
            }
        });
        chk_otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    otro_ant = "1";
                    hideKeyboard(layout);
                }
            }
        });

        chk_sangre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sangre = "si";
                    donar = "si";
                    chk_no.setChecked(false);
                    hideKeyboard(view);
                } else {
                    sangre = "no";
                    if (chk_sangre.isChecked() == false && chk_organos.isChecked() == false) {
                        chk_no.setChecked(true);
                    }
                }
            }
        });

        chk_organos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked) {
                    // Toast.makeText(getBaseContext(), "Donar Oganos", Toast.LENGTH_LONG).show();
                    organos = "si";
                    donar = "si";
                    chk_no.setChecked(false);
                    hideKeyboard(view);
                } else {
                    organos = "no";
                    if (chk_sangre.isChecked() == false && chk_organos.isChecked() == false) {
                        chk_no.setChecked(true);
                    }
                }
            }
        });
        chk_no.setChecked(true);
        chk_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();

                if (isChecked) {
                    //     Toast.makeText(getBaseContext(), "No donar", Toast.LENGTH_LONG).show();
                    donar = "no";
                    organos = "no";
                    sangre = "no";
                    chk_sangre.setChecked(false);
                    chk_organos.setChecked(false);
                    hideKeyboard(view);
                } else {

                    if (chk_organos.isChecked() == false && chk_sangre.isChecked() == false) {
                        chk_no.setChecked(true);
                    }
                }
            }
        });

    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void controlesDesplegables(View v) {
        //linearlayout para abrir y cerrar los datos generales
        lnr_header_generales = (LinearLayout) v.findViewById(R.id.header_generales);
        lnr_expand_generales = (LinearLayout) v.findViewById(R.id.expandable_generales);
        //linearlayout para abrir y cerrar los datos fisicos
        lnr_header_fisicos = (LinearLayout) v.findViewById(R.id.header_fisicos);
        lnr_expand_fisicos = (LinearLayout) v.findViewById(R.id.expandable_fisicos);
        //linearlayout para abrir y cerrar los datos del seguro
        lnr_header_seguros = (LinearLayout) v.findViewById(R.id.header_seguros);
        lnr_expand_seguros = (LinearLayout) v.findViewById(R.id.expandable_seguros);
        //
        lnr_header_alergias = (LinearLayout) v.findViewById(R.id.lnr_header_alergias);
        lnr_expand_alergias = (LinearLayout) v.findViewById(R.id.lnr_expand_alergias);
        //
        lnr_header_embarazo = (LinearLayout) v.findViewById(R.id.lnr_embarazada);
        lnr_expand_embarazo = (LinearLayout) v.findViewById(R.id.lnr_semanas_embarazo);
        //
        lnr_expand_segMedico = (LinearLayout) v.findViewById(R.id.lnr_expand_segMedico);
        lnr_expandOtroSeguro = (LinearLayout) v.findViewById(R.id.lnr_expandOtroSeg);
        //datos generales Cerrados
        lnr_expand_generales.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        lnr_expand_generales.getViewTreeObserver().removeOnPreDrawListener(this);
                        lnr_expand_generales.setVisibility(View.GONE);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        lnr_expand_generales.measure(widthSpec, heightSpec);
                        mAnimator = slideAnimator(0, lnr_expand_generales.getMeasuredHeight(), lnr_expand_generales);
                        return true;
                    }
                });
        //boton para abrir o cerrar los datos generales
        lnr_header_generales.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (lnr_expand_generales.getVisibility() == View.GONE) {
                    img_generales.setBackground(getResources().getDrawable(R.drawable.ic_flecha_arriba));
                    expandir(lnr_expand_generales);
                    colapsar(lnr_expand_fisicos);
                    colapsar(lnr_expand_seguros);
                } else {
                    img_generales.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(lnr_expand_generales);

                }
            }
        });
        //datos fiscos cerrados
        lnr_expand_fisicos.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        lnr_expand_fisicos.getViewTreeObserver().removeOnPreDrawListener(this);
                        lnr_expand_fisicos.setVisibility(View.GONE);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        lnr_expand_fisicos.measure(widthSpec, heightSpec);
                        mAnimatorFisico = slideAnimator(0, lnr_expand_fisicos.getMeasuredHeight(), lnr_expand_fisicos);
                        return true;
                    }
                });
        //boton para abrir o cerrar los datos fisicos
        lnr_header_fisicos.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (lnr_expand_fisicos.getVisibility() == View.GONE) {
                    img_fisicos.setBackground(getResources().getDrawable(R.drawable.ic_flecha_arriba));
                    expandir(lnr_expand_fisicos);
                    colapsar(lnr_expand_generales);
                    colapsar(lnr_expand_seguros);

                } else {
                    img_fisicos.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(lnr_expand_fisicos);
                }
            }
        });
        //datos Seguros cerrados
        lnr_expand_seguros.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        lnr_expand_seguros.getViewTreeObserver().removeOnPreDrawListener(this);
                        lnr_expand_seguros.setVisibility(View.GONE);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        lnr_expand_seguros.measure(widthSpec, heightSpec);
                        mAnimatorSeguro = slideAnimator(0, lnr_expand_seguros.getMeasuredHeight(), lnr_expand_seguros);
                        return true;
                    }
                });
        //boton para abrir o cerrar los datos fisicos
        lnr_header_seguros.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (lnr_expand_seguros.getVisibility() == View.GONE) {
                    img_seguros.setBackground(getResources().getDrawable(R.drawable.ic_flecha_arriba));
                    expandir(lnr_expand_seguros);
                    colapsar(lnr_expand_generales);
                    colapsar(lnr_expand_fisicos);

                } else {
                    img_seguros.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(lnr_expand_seguros);
                }
            }
        });

        lnr_expand_alergias.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                lnr_expand_alergias.getViewTreeObserver().removeOnPreDrawListener(this);
                lnr_expand_alergias.setVisibility(View.GONE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnr_expand_alergias.measure(widthSpec, heightSpec);
                mAnimatorAlergias = slideAnimator(0, lnr_expand_alergias.getMeasuredHeight(), lnr_expand_alergias);
                return true;
            }
        });
        lnr_expandOtroSeguro.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                lnr_expandOtroSeguro.getViewTreeObserver().removeOnPreDrawListener(this);
                lnr_expandOtroSeguro.setVisibility(View.GONE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnr_expandOtroSeguro.measure(widthSpec, heightSpec);
                mAnimatorOtroSeguro = slideAnimator(0, lnr_expandOtroSeguro.getMeasuredHeight(), lnr_expandOtroSeguro);
                return true;
            }
        });

        lnr_expand_embarazo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                lnr_expand_embarazo.getViewTreeObserver().removeOnPreDrawListener(this);
                lnr_expand_embarazo.setVisibility(View.GONE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnr_expand_embarazo.measure(widthSpec, heightSpec);
                manimatorEmbarazo = slideAnimator(0, lnr_expand_embarazo.getMeasuredHeight(), lnr_expand_embarazo);
                return true;
            }
        });

    }

    private void expandir(LinearLayout lnr) {
        //set Visible
        lnr.setVisibility(View.VISIBLE);
//		 Remove and used in preDrawListener
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        lnr.measure(widthSpec, heightSpec);
        mAnimator = slideAnimator(0, lnr.getMeasuredHeight(), lnr);
        mAnimator.start();
    }

    private void colapsar(final LinearLayout lnrexpand) {
        int finalHeight = lnrexpand.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0, lnrexpand);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                lnrexpand.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(int start, int end, final LinearLayout lnrExpand) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = lnrExpand.getLayoutParams();
                layoutParams.height = value;
                lnrExpand.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


}
