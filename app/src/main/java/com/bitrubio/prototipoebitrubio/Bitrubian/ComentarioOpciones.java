package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 26/07/2016.
 * selecciona el tipo de acceso y vista de los comentarios  y regresa el valor seleccionada ala actividad anterior
 */
public class ComentarioOpciones extends AppCompatActivity {

    @Bind(R.id.rd_privado)
    CheckBox chkPrivado;

    @Bind(R.id.rd_porra)
    CheckBox chkPorra;

    @Bind(R.id.rd_comunidad)
    CheckBox chkComunidad;

    @Bind(R.id.rd_publico)
    CheckBox chkPublico;

    Bundle bundle ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_comentario);
        ButterKnife.bind(this);
        bundle = new Bundle();
        final Intent intentBack = new Intent(ComentarioOpciones.this,ComentarioActivity.class);
        intentBack.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        chkPrivado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chkPrivado.setChecked(true);
                    chkPorra.setChecked(false);
                    chkComunidad.setChecked(false);
                    chkPublico.setChecked(false);
                    bundle.putInt("tipocomentario",1);
                    intentBack.putExtras(bundle);
                    startActivity(intentBack);
                }
            }
        });
        chkPorra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chkPrivado.setChecked(false);
                    chkPorra.setChecked(true);
                    chkComunidad.setChecked(false);
                    chkPublico.setChecked(false);
                    bundle.putInt("tipocomentario",2);
                    intentBack.putExtras(bundle);
                    startActivity(intentBack);
                }
            }
        });
        chkComunidad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chkPrivado.setChecked(false);
                    chkPorra.setChecked(false);
                    chkComunidad.setChecked(true);
                    chkPublico.setChecked(false);
                    bundle.putInt("tipocomentario",3);
                    intentBack.putExtras(bundle);
                    startActivity(intentBack);
                }
            }
        });
        chkPublico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chkPrivado.setChecked(false);
                    chkPorra.setChecked(false);
                    chkComunidad.setChecked(false);
                    chkPublico.setChecked(true);
                    bundle.putInt("tipocomentario",0);
                    intentBack.putExtras(bundle);
                    startActivity(intentBack);
                }
            }
        });
    }
}
