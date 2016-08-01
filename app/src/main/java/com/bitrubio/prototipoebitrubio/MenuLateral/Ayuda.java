package com.bitrubio.prototipoebitrubio.MenuLateral;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.CustomTypefaceSpan;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Orion on 11/05/2016.
 */
public class Ayuda extends AppCompatActivity implements View.OnClickListener{


    String TAG = getClass().getName();
    SessionManager session;
    Toolbar toolbar;
    Typeface tf;
    NavigationView navView;

    String idUsuario;
    ImageView imagePerfil;
    TextView txt_condiciones;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_menu_new);
        setSupportActionBar(toolbar);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        imagePerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imagePerfil);
        TextView nombreSession = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombreSession);
        nombreSession.setText(nameSession + " " + apeSession);
        nombreSession.setTypeface(tf);

        txt_condiciones = (TextView) navigationView.findViewById(R.id.terminos_condiciones);
        txt_condiciones.setTypeface(tf);


        TextView cerrarSession = (TextView) findViewById(R.id.cerrarSession);
        cerrarSession.setTypeface(tf);
        cerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        Picasso.with(this)
                .load("http://www.meustech.com:8080/bitrubio/movil/fotosPerfil/" + idUsuario + ".jpg")
                .into(imagePerfil);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.nav_premium:
                Toast.makeText(getBaseContext(), "Premium", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_agenda:
                Toast.makeText(getBaseContext(), "Agenda", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_tarjeta:
                Toast.makeText(getBaseContext(), "Tarjeta", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_promociones:
                Toast.makeText(getBaseContext(), "Promociones", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_facturas:
                Toast.makeText(getBaseContext(), "Facturas", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_configuracion:
                Toast.makeText(getBaseContext(), "Configuracion", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_ayuda:
                // Toast.makeText(getBaseContext(), "Ayuda", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Ayuda.this, Ayuda.class);
                startActivity(intent);
                //Your Operation
                break;
        }
    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    private void setTitleMenu() {
        navView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
