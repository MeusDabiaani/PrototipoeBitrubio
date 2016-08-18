package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.CustomTypefaceSpan;
import com.bitrubio.prototipoebitrubio.FragmentAmigos;
import com.bitrubio.prototipoebitrubio.FragmentDependientes;
import com.bitrubio.prototipoebitrubio.FragmentFamiliares;
import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.MenuLateral.Ayuda;
import com.bitrubio.prototipoebitrubio.MenuLateral.Premium;
import com.bitrubio.prototipoebitrubio.MenuLateral.Promociones;
import com.bitrubio.prototipoebitrubio.MenuLateral.Tarjeta;
import com.bitrubio.prototipoebitrubio.MetaEnfermedad;
import com.bitrubio.prototipoebitrubio.MetaFisica;
import com.bitrubio.prototipoebitrubio.MetaSocial;
import com.bitrubio.prototipoebitrubio.R;
import com.bitrubio.prototipoebitrubio.SignupVideo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 19/01/2016.
 * Muetra la vista y tab de la seccion de comunidad
 */
public class ComunidadActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    Toolbar toolbar;
    //botonoes superiores
    @Bind(R.id.btn_comunidad_dependientes)
    ImageButton _btnDependientes;
    @Bind(R.id.btn_comunidad_familiares)
    ImageButton _btnFamiliares;
    @Bind(R.id.btn_comunidad_amigos)
    ImageButton _btnAmigos;

    ImageView imagePerfil;

    //Barra Inferior
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

    FragmentTransaction FT;
    SessionManager session;
    Bitmap bitmap = null;
    Bitmap myBitmap;
    String idUsuario;
    NavigationView navigationView;
    DrawerLayout drawer;
    LinearLayout lnr_premium,lnr_agenda,lnr_tarjeta,lnr_promociones,lnr_facturas,lnr_configuracion,lnr_ayuda;

    ConectaServidor servidor;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunidad);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        session.checkLogin();
        servidor = new ConectaServidor();
        // get datos de usuario
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);

        // navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_comunidad);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        menuLateral();
        imagePerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imagePerfil);
        TextView nombreSession = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombreSession);
        nombreSession.setText(nameSession + " " + apeSession);
        TextView cerrarSession = (TextView) findViewById(R.id.cerrarSession);

        // set foto de perfil en el navigation
        File fileSDcard = Environment.getExternalStorageDirectory();
        File fileFotoPerfil = new File(fileSDcard, "imagen.jpg");
        if (fileFotoPerfil.exists()) {
            myBitmap = BitmapFactory.decodeFile(fileFotoPerfil.getAbsolutePath());
            imagePerfil.setImageBitmap(myBitmap);
        } else {
            Picasso.with(this)
                    .load(servidor.getUrl()+"fotosPerfil/" + idUsuario + ".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(imagePerfil);
        }
        cerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        _btnDependientes.setBackground(getResources().getDrawable(R.drawable.comunidad_dependientes_on));

        Fragment fragment = new FragmentDependientes();
        FT = getFragmentManager().beginTransaction();
        FT.add(R.id.fragment_container_comunidad, fragment);
        FT.commit();

        _btnDependientes.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                _btnDependientes.setBackground(getResources().getDrawable(R.drawable.comunidad_dependientes_on));
                _btnAmigos.setBackground(getResources().getDrawable(R.drawable.comunidad_amigos_off));
                _btnFamiliares.setBackground(getResources().getDrawable(R.drawable.comunidad_familiares_off));
                final Fragment fragmenteDependientes = new FragmentDependientes();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_comunidad, fragmenteDependientes);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _btnFamiliares.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                _btnFamiliares.setBackground(getResources().getDrawable(R.drawable.comunidad_familiares_on));
                _btnDependientes.setBackground(getResources().getDrawable(R.drawable.comunidad_dependientes_off));
                _btnAmigos.setBackground(getResources().getDrawable(R.drawable.comunidad_amigos_off));
                final Fragment fragmentFamiliares = new FragmentFamiliares();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_comunidad, fragmentFamiliares);
                FT.addToBackStack(null);
                FT.commit();
            }
        });


        _btnAmigos.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                _btnAmigos.setBackground(getResources().getDrawable(R.drawable.comunidad_amigos_on));
                _btnFamiliares.setBackground(getResources().getDrawable(R.drawable.comunidad_familiares_off));
                _btnDependientes.setBackground(getResources().getDrawable(R.drawable.comunidad_dependientes_off));
                //_btnAmigos.setPressed(true);
                final Fragment fragmentAmigos = new FragmentAmigos();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_comunidad, fragmentAmigos);
                FT.addToBackStack(null);
                FT.commit();
            }
        });


        _btnComunidad.setBackground(getResources().getDrawable(R.drawable.ic_comunidad_b));

        seccionBotones();
    }
    public void seccionBotones(){
        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ComunidadActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Comunidad", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ComunidadActivity.this, ComunidadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });

        _btnAlerta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //  Toast.makeText(getBaseContext(),"Alerta",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ComunidadActivity.this, AlertaActvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnExpertos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //  Toast.makeText(getBaseContext(),"Expertos",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ComunidadActivity.this, ExpertosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnServicios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ComunidadActivity.this, ServiciosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);

            }
        });
    }

    public void menuLateral(){

        lnr_premium = (LinearLayout) navigationView.findViewById(R.id.nav_premium);
        lnr_agenda = (LinearLayout) navigationView.findViewById(R.id.nav_agenda);
        lnr_tarjeta = (LinearLayout) navigationView.findViewById(R.id.nav_tarjeta);
        lnr_facturas = (LinearLayout) navigationView.findViewById(R.id.nav_facturas);
        lnr_promociones = (LinearLayout) navigationView.findViewById(R.id.nav_promociones);
        lnr_configuracion = (LinearLayout) navigationView.findViewById(R.id.nav_configuracion);
        lnr_ayuda = (LinearLayout) navigationView.findViewById(R.id.nav_ayuda);

        lnr_premium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent inten_premium = new Intent(ComunidadActivity.this, Premium.class);
                inten_premium.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten_premium);
            }
        });
        lnr_agenda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
            }
        });
        lnr_tarjeta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_tarjeta = new Intent(ComunidadActivity.this, Tarjeta.class);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_tarjeta);
            }
        });
        lnr_promociones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_promociones = new Intent(ComunidadActivity.this, Promociones.class);
                intent_promociones.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_promociones);
            }
        });
        lnr_facturas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
            }
        });
        lnr_configuracion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
            }
        });
        lnr_ayuda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_ayuda = new Intent(ComunidadActivity.this, Ayuda.class);
                intent_ayuda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_ayuda);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }




    //metodos para setear los titulos del navigationdrawer
}
