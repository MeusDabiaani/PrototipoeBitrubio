package com.bitrubio.prototipoebitrubio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Entidades.Comunidad;

import java.util.ArrayList;

/**
 * Created by Orion on 25/05/2016.
 */
public class SolicitudesAdapter extends ArrayAdapter<Comunidad> {
    private final Context context;
    private final ArrayList<Comunidad> values;
    Typeface tf;
    Animator mAnimator;
    LinearLayout rl_cabecera, rl_expand;

    public SolicitudesAdapter(Context context, ArrayList<Comunidad> values) {
        super(context, R.layout.content_solictudes_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_solictudes_row, parent, false);
        tf = Typeface.createFromAsset(context.getAssets(), "fonts/avenir-light.ttf");
        ImageView imagePerson = (ImageView) rowView.findViewById(R.id.img_comunidad_amigo);
        TextView textNombre = (TextView) rowView.findViewById(R.id.txt_nombre_sol);
        final CheckBox chkBox = (CheckBox) rowView.findViewById(R.id.txt_check_sol);
        textNombre.setText(values.get(position).getNombre());

        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context, R.style.DialogTheme);
                dialog.setContentView(R.layout.fragment_dialog_solicitud);

                inicializa_dialog(dialog);

                dialog.show();

            }
        });
        rowView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ID BORRADO  " + values.get(position).getId(), Toast.LENGTH_SHORT).show();

                notifyDataSetChanged();

            }
        });
        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @SuppressLint("WrongViewCast")
    private void inicializa_dialog(final Dialog dialog) {


        CheckBox chk_amigo = (CheckBox) dialog.findViewById(R.id.chk_amigo);
        CheckBox chk_padres = (CheckBox) dialog.findViewById(R.id.chk_padre);
        CheckBox chk_pareja = (CheckBox) dialog.findViewById(R.id.chk_pareja);
        CheckBox chk_abueloMat = (CheckBox) dialog.findViewById(R.id.chk_abuelo_materno);
        CheckBox chk_abueloPat = (CheckBox) dialog.findViewById(R.id.chk_abuelo_paterno);
        CheckBox chk_hermanos = (CheckBox) dialog.findViewById(R.id.chk_hermano);
        CheckBox chk_tio = (CheckBox) dialog.findViewById(R.id.chk_tio);
        CheckBox chk_primo = (CheckBox) dialog.findViewById(R.id.chk_primo);


        rl_expand = (LinearLayout) dialog.findViewById(R.id.rl_expandrl_expand);
        rl_cabecera = (LinearLayout) dialog.findViewById(R.id.rl_cabecera_opciones);


        chk_amigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_padres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_abueloMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_abueloPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_hermanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_tio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_primo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        chk_pareja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        rl_expand.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        rl_expand.getViewTreeObserver().removeOnPreDrawListener(this);
                        rl_expand.setVisibility(View.GONE);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        rl_expand.measure(widthSpec, heightSpec);
                        mAnimator = slideAnimator(0, rl_expand.getMeasuredHeight(), rl_expand);
                        return true;
                    }
                });
        //boton para abrir o cerrar los datos generales
        rl_cabecera.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (rl_expand.getVisibility() == View.GONE) {
                    //img_generales.setBackground(getResources().getDrawable(R.drawable.ic_flecha_arriba));
                    expandir(rl_expand);

                } else {
                    // img_generales.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    colapsar(rl_expand);

                }
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

}

