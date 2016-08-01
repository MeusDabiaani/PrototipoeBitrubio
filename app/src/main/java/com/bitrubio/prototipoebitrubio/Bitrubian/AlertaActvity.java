package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.IntroActivity;
import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.R;
import com.bitrubio.prototipoebitrubio.SignupActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 20/01/2016.
 */
public class AlertaActvity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Bind(R.id.btn_cancel_alerta)
    Button _btnCancelarAlerta1;

    @Bind(R.id.btn_ayudar_otro)
    Button _btn_ayudar_otro;


    @Bind(R.id.txt_Alerta)
    TextView txt_time;
    CountDownTimer waitTimer;
    SessionManager session;
    Bitmap myBitmap = null;
    String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
        session = new SessionManager(getApplicationContext());
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);

        waitTimer =  new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                txt_time.setText("En : " + millisUntilFinished / 1000 + " segundos \n Se activara la alerta");
            }

            public void onFinish() {
                txt_time.setText("Alerta Activa");
                Intent intent = new Intent(AlertaActvity.this,AlertaLlamandoActivity.class);
                startActivity(intent);


            }
        }.start();


        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                    .load("http://www.meustech.com:8080/bitrubio/movil/fotosPerfil/"+idUsuario+".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(imagePerfil);
        }
        seccionBotones();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tarjeta) {
            //Toast.makeText(this, "Actividad 1", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_promociones) {
            //Toast.makeText(this,"Actividad 2",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_ayuda) {
            //Toast.makeText(this,"Actividad 3 ",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_configuracion) {
            //Toast.makeText(this,"Actividad 4",Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        item.setActionView(null);
        return true;
    }
    public void seccionBotones(){

        _btnCancelarAlerta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getBaseContext(), "Cancelar Alarma", Toast.LENGTH_SHORT).show();
                waitTimer.cancel();
                Intent intent = new Intent(AlertaActvity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        _btn_ayudar_otro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                waitTimer.cancel();
                Intent intent = new Intent(AlertaActvity.this,AyudarOtroActivity.class);
                startActivity(intent);
            }
        });

    }

}
