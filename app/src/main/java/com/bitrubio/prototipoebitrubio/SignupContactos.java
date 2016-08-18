package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 08/01/2016.
 * panatalla muestra contactos
 */
public class SignupContactos extends Activity {

    @Bind(R.id.btn_salud)
    Button _btnSalud;
    @Bind(R.id.btn_otros)
    Button _btnOtros;
    @Bind(R.id.btn_perfil_medico)
    Button _btnperfilMedico;
    TextView msg,txtMedium;
    SessionManager session;
    String sMedium;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_contactos);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf");
        msg = (TextView) findViewById(R.id.txt_selecciona_quehacer);
        msg.setText("¡Listo!");

        sMedium = "¿Qué quieres hacer primero?";
        txtMedium = (TextView) findViewById(R.id.txt_medium);
        txtMedium.setText(sMedium);

        _btnSalud.setTypeface(tf);
        _btnOtros.setTypeface(tf);
        _btnperfilMedico.setTypeface(tf);


        _btnSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v,"cuidar tu salud",Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(SignupContactos.this,MainActivity.class);
                startActivity(intent);
            }
        });
        _btnOtros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Snackbar.make(v,"cuidar la salud de otros",Snackbar.LENGTH_LONG).show();
            }
        });
        _btnperfilMedico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                session.checkLogin();
                Intent intent = new Intent(SignupContactos.this,CompletaPerfilActivity.class);
                startActivity(intent);
            }
        });
    }
}
