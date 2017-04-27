package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Experto;
import com.bitrubio.prototipoebitrubio.ExpertosAdapter;
import com.bitrubio.prototipoebitrubio.FragmentCoaches;
import com.bitrubio.prototipoebitrubio.FragmentExpertos;
import com.bitrubio.prototipoebitrubio.FragmentPatrocinadores;
import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.MenuLateral.Ayuda;
import com.bitrubio.prototipoebitrubio.MenuLateral.Premium;
import com.bitrubio.prototipoebitrubio.MenuLateral.Promociones;
import com.bitrubio.prototipoebitrubio.MenuLateral.Tarjeta;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 27/01/2016.
 * Muestra los fragmentos de la seccion expertos
 */
//TODO falta crear el backend con la lista de medicos registrados , mas una Searchview para esa lista
public class ExpertosActivity extends AppCompatActivity {


    String TAG = "ExpertosActivity";
    Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ExpertosAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Experto> expertoList;
    @Bind(R.id.btn_expertos_exp)
    ImageButton _btnExp;
    @Bind(R.id.btn_expertos_coaches)
    ImageButton _btnCoaches;
    @Bind(R.id.btn_expertos_patrocinadores)
    ImageButton _btnPatrocinadores;
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
    Bitmap myBitmap = null;
    String idUsuario;
    ConectaServidor servidor;
    NavigationView navigationView;
    DrawerLayout drawer;
    LinearLayout lnr_premium,lnr_agenda,lnr_tarjeta,lnr_promociones,lnr_facturas,lnr_configuracion,lnr_ayuda;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expertos);
        session = new SessionManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        ButterKnife.bind(this);
        session.checkLogin();
        servidor = new ConectaServidor();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);

        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_expertos);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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

        Fragment fragmentExpertos = new FragmentExpertos();
        FT = getFragmentManager().beginTransaction();
        FT.add(R.id.fragment_container_expertos, fragmentExpertos);
        FT.commit();

        _btnCoaches.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                _btnCoaches.setBackground(getResources().getDrawable(R.drawable.coaches_on));
                _btnPatrocinadores.setBackground(getResources().getDrawable(R.drawable.patrocinadores_off));
                _btnExp.setBackground(getResources().getDrawable(R.drawable.expertos_off));
                final Fragment fragment = new FragmentCoaches();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_expertos, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _btnPatrocinadores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _btnCoaches.setBackground(getResources().getDrawable(R.drawable.coaches_off));
                _btnPatrocinadores.setBackground(getResources().getDrawable(R.drawable.patrocinadores_on));
                _btnExp.setBackground(getResources().getDrawable(R.drawable.expertos_off));
                final Fragment fragment = new FragmentPatrocinadores();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_expertos, fragment);
                FT.addToBackStack(null);
                FT.commit();
            }
        });

        _btnExp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _btnCoaches.setBackground(getResources().getDrawable(R.drawable.coaches_off));
                _btnPatrocinadores.setBackground(getResources().getDrawable(R.drawable.patrocinadores_off));
                _btnExp.setBackground(getResources().getDrawable(R.drawable.expertos_on));
                final Fragment fragmentExpertos = new FragmentExpertos();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_container_expertos, fragmentExpertos);
                FT.addToBackStack(null);
                FT.commit();
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
        _btnExpertos.setBackground(getResources().getDrawable(R.drawable.ic_consultas_b));
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_expertos);
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
                Intent intent_premium = new Intent(ExpertosActivity.this, Premium.class);
                intent_premium.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                Intent intent_tarjeta = new Intent(ExpertosActivity.this, Tarjeta.class);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_tarjeta);
            }
        });
        lnr_promociones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_promociones = new Intent(ExpertosActivity.this, Promociones.class);
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
                Intent intent_ayuda = new Intent(ExpertosActivity.this, Ayuda.class);
                intent_ayuda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_ayuda);
            }
        });
    }

    public void seccionBotones(){
        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpertosActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Comunidad", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpertosActivity.this, ComunidadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });

        _btnAlerta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  Toast.makeText(getBaseContext(),"Alerta",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpertosActivity.this, AlertaActvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnExpertos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  Toast.makeText(getBaseContext(),"Expertos",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpertosActivity.this, ExpertosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
            }
        });
        _btnServicios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExpertosActivity.this, ServiciosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);

            }
        });
    }
    //metodos para setear los titulos del navigationdrawer


}
