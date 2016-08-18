package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.annotation.TargetApi;
import android.content.Intent;
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
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
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
import com.bitrubio.prototipoebitrubio.IntroActivity;
import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.MenuLateral.Ayuda;
import com.bitrubio.prototipoebitrubio.MenuLateral.Premium;
import com.bitrubio.prototipoebitrubio.MenuLateral.Promociones;
import com.bitrubio.prototipoebitrubio.MenuLateral.Tarjeta;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 27/01/2016.
 */
public class ServiciosActivity extends AppCompatActivity {
    private String TAG = "ServicisoActivity";
    Toolbar toolbar;
    String idUsuario;
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
    SessionManager session;
    Bitmap myBitmap = null;
    CardView carViewFarmacia, cardViewSubastas, cardViewDonacion;
    NavigationView navigationView;
    DrawerLayout drawer;
    LinearLayout lnr_premium, lnr_agenda, lnr_tarjeta, lnr_promociones, lnr_facturas, lnr_configuracion, lnr_ayuda;
    ConectaServidor servidor;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        session = new SessionManager(getApplicationContext());
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_menu_new);
        setSupportActionBar(toolbar);

        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_servicios);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toogle);
        toogle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menuLateral();

        ImageView imagePerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imagePerfil);
        TextView nombreSession = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombreSession);
        nombreSession.setText(nameSession + " " + apeSession);
        TextView cerrarSession = (TextView) findViewById(R.id.cerrarSession);
        cerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();

            }
        });


        File fileSDcard = Environment.getExternalStorageDirectory();
        File fileFotoPerfil = new File(fileSDcard, "imagen.jpg");
        if (fileFotoPerfil.exists()) {
            myBitmap = BitmapFactory.decodeFile(fileFotoPerfil.getAbsolutePath());
            imagePerfil.setImageBitmap(myBitmap);
        } else {
            Picasso.with(this)
                    .load(servidor.getUrl()+"fotosPerfil/"+idUsuario+".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(imagePerfil);
        }

        seccionBotones();

        carViewFarmacia = (CardView) findViewById(R.id.card_view_farmacia);
        carViewFarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFarmacia = new Intent(ServiciosActivity.this, FarmaciaActivity.class);
                startActivity(intentFarmacia);
            }
        });

        cardViewSubastas = (CardView) findViewById(R.id.card_view_subastas);
        cardViewSubastas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSubasta = new Intent(ServiciosActivity.this, SubastasActivity.class);
                startActivity(intentSubasta);
            }
        });
        cardViewDonacion = (CardView) findViewById(R.id.card_view_donacion);
        cardViewDonacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDonacion = new Intent(ServiciosActivity.this, DonacionActivity.class);
                startActivity(intentDonacion);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_servicios);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.action_helpBeety) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void seccionBotones() {

        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiciosActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Comunidad", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiciosActivity.this, ComunidadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });

        _btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"Alerta",Toast.LENGTH_SHORT).show()tar ;
                Intent intent = new Intent(ServiciosActivity.this, AlertaActvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnExpertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"Expertos",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiciosActivity.this, ExpertosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiciosActivity.this, ServiciosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);

            }
        });
    }
    //metodos para setear los titulos del navigationdrawer
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
                Intent intent_premium = new Intent(ServiciosActivity.this, Premium.class);
                intent_premium.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_premium);
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
                Intent intent_tarjeta = new Intent(ServiciosActivity.this, Tarjeta.class);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_tarjeta);
            }
        });
        lnr_promociones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_promociones = new Intent(ServiciosActivity.this, Promociones.class);
                intent_promociones.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
                Intent intent_ayuda = new Intent(ServiciosActivity.this, Ayuda.class);
                intent_ayuda.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_ayuda);
            }
        });
    }
}


