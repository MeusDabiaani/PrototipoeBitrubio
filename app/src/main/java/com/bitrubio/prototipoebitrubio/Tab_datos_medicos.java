package com.bitrubio.prototipoebitrubio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Entidades.Consultas;

import java.util.ArrayList;

/**
 * Created by Orion on 29/02/2016.
 */
public class Tab_datos_medicos extends Fragment {
    LinearLayout lnr_expand_historial,lnr_header_historial,lnr_cuerpo;
    ValueAnimator mAnimator;
    ImageView img_flecha;
    EditText edit_buscar;
    ImageButton btn_cuello;
    private RecyclerView mRecyclerView;
    private ConsultasAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Consultas> consultList;
    Typeface tf;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_datos_medicos, container, false);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
       // hideKeyboard(getActivity());
        edit_buscar = (EditText) v.findViewById(R.id.edit_buscar);
        edit_buscar.setTypeface(tf,Typeface.ITALIC);
        edit_buscar.setTextSize(14);
        lnr_cuerpo = (LinearLayout) v.findViewById(R.id.lnr_cuerpo);
        btn_cuello = (ImageButton) v.findViewById(R.id.btn_cuello);
        lnr_header_historial = (LinearLayout) v.findViewById(R.id.header_historial_medico);
        lnr_expand_historial = (LinearLayout) v.findViewById(R.id.expandable_historial_medicos);
        img_flecha = (ImageView) v.findViewById(R.id.img_flecha_arriba);

        consultList = new ArrayList<Consultas>();
        consultList.add(new Consultas(0,1,1,"Influenza","Antibioticos,reposo 5 dias ","11/feb/2016","Tomar liquidos diariamente",200.0f,1,5 ));
        consultList.add(new Consultas(1,2,3,"Diabetes","Paracetamol","10/jun/2016","Cuidar alimentacion",300.0f,1,5 ));
        consultList.add(new Consultas(2,3,4,"Hombro dislocado","Paracetamol 500mg x 8 dias","01/jun/2016","Guardar reposo",200.0f,1,5 ));
        consultList.add(new Consultas(3,1,1,"Influenza","Antibioticos,reposo 5 dias ","11/mar/2016","Tomar liquidos diariamente",200.0f,1,5 ));
        consultList.add(new Consultas(4,2,3,"Diabetes","Paracetamol","10/abr/2016","Cuidar alimentacion",300.0f,1,5 ));
        consultList.add(new Consultas(5,3,4,"Hombro dislocado","Paracetamol 500mg x 8 dias","01/may/2016","Guardar reposo",200.0f,1,5 ));

        closeKeyboard(getActivity(), edit_buscar.getWindowToken());
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_datos_medicos);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ConsultasAdapter(consultList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);


        edit_buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }

        });

        btn_cuello.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Cuello sin enfermedades", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        lnr_expand_historial.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        lnr_expand_historial.getViewTreeObserver().removeOnPreDrawListener(this);
                        lnr_expand_historial.setVisibility(View.GONE);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        lnr_expand_historial.measure(widthSpec, heightSpec);
                        mAnimator = slideAnimator(0, lnr_expand_historial.getMeasuredHeight(),lnr_expand_historial);
                        return true;
                    }
                });
        lnr_cuerpo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
            @Override
            public boolean onPreDraw() {
                lnr_cuerpo.getViewTreeObserver().removeOnPreDrawListener(this);
                lnr_cuerpo.setVisibility(View.VISIBLE);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                lnr_cuerpo.measure(widthSpec, heightSpec);
                mAnimator = slideAnimator(0, lnr_cuerpo.getMeasuredHeight(),lnr_cuerpo);
                return true;
            }
        });
        //boton para abrir o cerrar los datos generales
        lnr_header_historial.setOnClickListener(new View.OnClickListener() {


            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (lnr_expand_historial.getVisibility() == View.GONE) {
                    img_flecha.setBackground(getResources().getDrawable(R.drawable.ic_flecha_abajo));
                    expand(lnr_expand_historial);
                    collapse(lnr_cuerpo);
                } else {
                    img_flecha.setBackground(getResources().getDrawable(R.drawable.ic_flecha_arriba));
                    collapse(lnr_expand_historial);
                    expand(lnr_cuerpo);
                }
            }
        });
        return v;
    }



    private void expand(LinearLayout lnr) {
        //set Visible
        lnr.setVisibility(View.VISIBLE);
//		 Remove and used in preDrawListener
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        lnr.measure(widthSpec, heightSpec);
        mAnimator = slideAnimator(0, lnr.getMeasuredHeight(),lnr);
        mAnimator.start();
    }
    private void collapse(final LinearLayout lnr) {
        int finalHeight = lnr.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0,lnr);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                lnr.setVisibility(View.GONE);
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
    private ValueAnimator slideAnimator(int start, int end, final LinearLayout lnr_layout) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = lnr_layout.getLayoutParams();
                layoutParams.height = value;
                lnr_layout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

}
