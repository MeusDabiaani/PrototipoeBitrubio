package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.DatePickerFragment;
import com.bitrubio.prototipoebitrubio.R;
import com.google.android.gms.vision.text.Line;

import java.util.Calendar;

import butterknife.Bind;

import butterknife.ButterKnife;

/**
 * Created by Orion on 04/07/2016.
 */
public class Tab_regSubastas extends Fragment {
    ValueAnimator mAnimator;
    LinearLayout lnrPregunta1, lnrContenido_a,  lnrEspecifica, lnrAyudarme, lnrComentarioAdicional;

    @Bind(R.id.img_flecha_uno)
    ImageView imgFlechaUno;

    @Bind(R.id.txtFecha)
    TextView txtFecha;

    @Bind(R.id.opc_1)
    CheckBox chkCirFuncional;

    @Bind(R.id.opc_2)
    CheckBox chkCirEstetica;

    @Bind(R.id.opc_3)
    CheckBox chhPadCronico;

    @Bind(R.id.opc_4)
    CheckBox chkTratamiento;

    @Bind(R.id.opc_5)
    CheckBox chkTratDental;

    @Bind(R.id.opc_6)
    ImageView chkOtro;

    @Bind(R.id.txtQuehacerte)
    TextView txtQueHacerte;

    @Bind(R.id.btn_siguiente)
    ImageButton imgSiguiente;

    @Bind(R.id.btn_cancelar)
    ImageButton imgCancelar;

    Calendar calender;




    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_registro_subastas, container, false);
        ButterKnife.bind(this, v);
        inicializaCampos(v);
        calender = Calendar.getInstance();
        txtFecha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showDatePicker();
                    return true;
                }
                return false;
            }
        });
        inicializaCheckbox();
        return v;
    }

    public void inicializaCampos(View v) {
        lnrPregunta1 = (LinearLayout) v.findViewById(R.id.pregunta_1);
        /*lnrPregunta2 = (LinearLayout) v.findViewById(R.id.pregunta_2);*/
        lnrContenido_a = (LinearLayout) v.findViewById(R.id.contenido_a);
        /*lnrContenido_b = (LinearLayout) v.findViewById(R.id.contenido_b);*/
        lnrEspecifica = (LinearLayout) v.findViewById(R.id.lnr_especifica);
        lnrAyudarme = (LinearLayout) v.findViewById(R.id.lnr_ayudarme);
        lnrComentarioAdicional = (LinearLayout) v.findViewById(R.id.pregunta_3);
        leeLinearLayouts();


        imgSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                DialogCompletarPerfil dialogCompletarPerfil= new DialogCompletarPerfil();
                dialogCompletarPerfil.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogTheme);
                dialogCompletarPerfil.show(fragmentManager,"Completa Perfil");
            }
        });

        imgCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogAvisoPerfil dialogAvisoPerfil= new DialogAvisoPerfil();
                dialogAvisoPerfil.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogTheme);
                dialogAvisoPerfil.show(fragmentManager,"Aviso Perfil");
            }
        });

    }

    public void inicializaCheckbox() {


        chkCirFuncional.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colapsar(lnrContenido_a);

                    chkCirEstetica.setChecked(false);
                    chhPadCronico.setChecked(false);
                    chkTratamiento.setChecked(false);
                    chkTratDental.setChecked(false);


                    txtQueHacerte.setText(getResources().getString(R.string.cirugia_a));
                    txtQueHacerte.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });

        chkCirEstetica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colapsar(lnrContenido_a);

                    chkCirFuncional.setChecked(false);
                    chhPadCronico.setChecked(false);
                    chkTratamiento.setChecked(false);
                    chkTratDental.setChecked(false);


                    txtQueHacerte.setText(getResources().getString(R.string.cirugia_b));
                    txtQueHacerte.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
        chhPadCronico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colapsar(lnrContenido_a);
                    chkCirFuncional.setChecked(false);
                    chkCirEstetica.setChecked(false);
                    chkTratamiento.setChecked(false);
                    chkTratDental.setChecked(false);

                    txtQueHacerte.setText(getResources().getString(R.string.cirugia_c));
                    txtQueHacerte.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
        chkTratamiento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colapsar(lnrContenido_a);

                    chkCirFuncional.setChecked(false);
                    chkCirEstetica.setChecked(false);
                    chhPadCronico.setChecked(false);
                    chkTratDental.setChecked(false);
                    txtQueHacerte.setText(getResources().getString(R.string.cirugia_d));
                    txtQueHacerte.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
        chkTratDental.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colapsar(lnrContenido_a);
                    chkCirFuncional.setChecked(false);
                    chkCirEstetica.setChecked(false);
                    chhPadCronico.setChecked(false);
                    chkTratamiento.setChecked(false);
                    txtQueHacerte.setText(getResources().getString(R.string.cirugia_e));
                    txtQueHacerte.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
        chkOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colapsar(lnrContenido_a);
                chkCirFuncional.setChecked(false);
                chkCirEstetica.setChecked(false);
                chhPadCronico.setChecked(false);
                chkTratDental.setChecked(false);
                chkTratamiento.setChecked(false);
                Intent intent = new Intent(getActivity(), Especifica.class);
                startActivity(intent);
            }
        });
    }
    public void leeLinearLayouts() {

        lnrEspecifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Especifica.class);
                startActivity(intent);
            }
        });
        lnrAyudarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_comentario = new Intent(getActivity(), ComentarioAyudarme.class);
                startActivity(intent_comentario);
            }
        });
        lnrComentarioAdicional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_adicional = new Intent(getActivity(), ComentarioAdicional.class);
                startActivity(intent_adicional);

            }
        });
        lnrContenido_a.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                lnrContenido_a.getViewTreeObserver().removeOnPreDrawListener(this);
                lnrContenido_a.setVisibility(View.GONE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnrContenido_a.measure(widthSpec, heightSpec);
                mAnimator = slideAnimator(0, lnrContenido_a.getMeasuredHeight(), lnrContenido_a);
                return true;
            }
        });

        lnrPregunta1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (lnrContenido_a.getVisibility() == View.GONE) {
                    expandir(lnrContenido_a);
                    imgFlechaUno.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_flecha_arriba));
                } else {
                    imgFlechaUno.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(lnrContenido_a);

                }
            }
        });
  /*      lnrContenido_b.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                lnrContenido_b.getViewTreeObserver().removeOnPreDrawListener(this);
                lnrContenido_b.setVisibility(View.GONE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnrContenido_b.measure(widthSpec, heightSpec);
                mAnimator = slideAnimator(0, lnrContenido_b.getMeasuredHeight(), lnrContenido_b);
                return true;
            }
        });
        lnrPregunta2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (lnrContenido_b.getVisibility() == View.GONE) {
                    expandir(lnrContenido_b);
                    imgFlechaDos.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_flecha_arriba));
                } else {
                    imgFlechaDos.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(lnrContenido_b);

                }
            }
        });*/
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
                //Height=0, but it set visibility to GONE
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
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = lnrExpand.getLayoutParams();
                layoutParams.height = value;
                lnrExpand.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");


    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String mes = null;
            if (monthOfYear + 1 == 1) {
                mes = "ene";
            }
            if (monthOfYear + 1 == 2) {
                mes = "feb";
            }
            if (monthOfYear + 1 == 3) {
                mes = "mar";
            }
            if (monthOfYear + 1 == 4) {
                mes = "abr";
            }
            if (monthOfYear + 1 == 5) {
                mes = "may";
            }
            if (monthOfYear + 1 == 6) {
                mes = "jun";
            }
            if (monthOfYear + 1 == 7) {
                mes = "jul";
            }
            if (monthOfYear + 1 == 8) {
                mes = "ago";
            }
            if (monthOfYear + 1 == 9) {
                mes = "sep";
            }
            if (monthOfYear + 1 == 10) {
                mes = "oct";
            }
            if (monthOfYear + 1 == 11) {
                mes = "nov";
            }
            if (monthOfYear + 1 == 12) {
                mes = "dic";
            }

            txtFecha.setText(getResources().getString(R.string.reg_pregunta4) + " " + String.valueOf(dayOfMonth) + "/" + mes + "/" + String.valueOf(year));
            //fecha_nacimiento = String.valueOf(year) + "-" + monthOfYear + 1 + "-" + String.valueOf(dayOfMonth);


        }
    };

}
