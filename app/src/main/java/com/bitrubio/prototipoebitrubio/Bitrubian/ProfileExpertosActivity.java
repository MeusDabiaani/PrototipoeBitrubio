package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 03/03/2016.
 */
public class ProfileExpertosActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navView;
    String TAG = getClass().getSimpleName();
    Typeface tf;
    @Bind(R.id.btn_home)
    ImageButton _btnHome;
    @Bind(R.id.btn_comunidad)
    ImageButton _btnComunidad;
    @Bind(R.id.btn_alerta)
    ImageButton _btnAlerta;
    @Bind(R.id.btn_expertos)
    ImageButton _btnExpertos;
    @Bind(R.id.btn_servicios)
    ImageButton _btnServicios;

    @Bind(R.id.llamar)
    ImageView _btnLlamada;
    @Bind(R.id.ubicacion)
    ImageView _btnUbicacion;
    @Bind(R.id.calendario)
    ImageView _btnAgenda;
    @Bind(R.id.videollamada)
    ImageView _btnVideollamada;
    @Bind(R.id.compartir)
    ImageView _btnCompartir;
    @Bind(R.id.mas)
    ImageView _btnMasDetalles;

    @Bind(R.id.image_medico)
    ImageView imgMedico;

    @Bind(R.id.txt_nomUsuario)
    TextView txtnomMedico;
    @Bind(R.id.txt_especialidad)
    TextView txtEspecialidad;
    @Bind(R.id.txt_direccion)
    TextView txtDomicilio;

    FragmentTransaction FT;
    Constantes mConstantes;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_expertos);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.profileExperto));
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        seccionBotones();
        inicializaCampos();

        Bundle extras = getIntent().getExtras();

        int idMedico = 0;
        if (extras != null) {
            idMedico = extras.getInt(mConstantes.C_ID);
        }
        Log.e(TAG,"idmedico"+idMedico);
        int drawable[] = {R.drawable.doctor_1, R.drawable.doctor_2, R.drawable.doctor_3};

        imgMedico.setImageDrawable(this.getResources().getDrawable(drawable[idMedico]));
        if (savedInstanceState==null) {
            Fragment fragment = new FragmentInfoExperto();
            FT = getSupportFragmentManager().beginTransaction();
            FT.add(R.id.fragment_container_profileExperto, fragment);
            FT.commit();
        }
        imgMedico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _btnMasDetalles.setBackground(getResources().getDrawable(R.drawable.ic_mas_a));
                _btnUbicacion.setBackground(getResources().getDrawable(R.drawable.ic_ubicacion_a));

                Fragment fragment = new FragmentInfoExperto();
                FT = getSupportFragmentManager().beginTransaction();
                FT.add(R.id.fragment_container_profileExperto, fragment);
                FT.commit();
            }
        });

        _btnLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "LLAMADA", Toast.LENGTH_SHORT).show();
            }
        });

        _btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "UBICACION", Toast.LENGTH_SHORT).show();
                _btnUbicacion.setBackground(getResources().getDrawable(R.drawable.ic_ubicacion_b));
                final FragmentUbicacion fragmentUbicacion = new FragmentUbicacion();
                FT = getSupportFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_profileExperto, fragmentUbicacion);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FragmentAgenda framentDate = new FragmentAgenda();
                FT = getSupportFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_profileExperto, framentDate);
                FT.addToBackStack(null);
                FT.commit();


            }
        });
        _btnVideollamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"VIDEOLLAMADA",Toast.LENGTH_SHORT).show();
            }
        });
        _btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Compartir"));




            }
        });
        _btnMasDetalles.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _btnMasDetalles.setBackground(getResources().getDrawable(R.drawable.ic_mas_b));
                Toast.makeText(getBaseContext(),"DETALLES",Toast.LENGTH_SHORT).show();
                final FragmentDetalleExperto FragmentDetalleExperto = new FragmentDetalleExperto();
                FT = getSupportFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_profileExperto, FragmentDetalleExperto);
                FT.addToBackStack(null);
                FT.commit();

            }
        });

    }

    private void inicializaCampos() {

        setTypeAndSize(txtnomMedico).setTypeface(tf,Typeface.BOLD);
        setTypeAndSize(txtEspecialidad);
        setTypeAndSize(txtDomicilio);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }



    public void seccionBotones() {
        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileExpertosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        _btnComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Comunidad", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileExpertosActivity.this, ComunidadActivity.class);
                startActivity(intent);
            }
        });

        _btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Alerta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileExpertosActivity.this, AlertaActvity.class);
                startActivity(intent);
            }
        });
        _btnExpertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Expertos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileExpertosActivity.this, ExpertosActivity.class);
                startActivity(intent);
            }
        });
        _btnServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getBaseContext(), "Servicios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextView setTypeAndSize(TextView textView) {
        textView.setTextSize(14);
        textView.setTypeface(tf);
        return textView;
    }
}
