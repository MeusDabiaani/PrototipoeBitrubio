package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.CompletaPerfilActivity;
import com.bitrubio.prototipoebitrubio.CustomTypefaceSpan;
import com.bitrubio.prototipoebitrubio.DividerItemDecoration;
import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.MensajesAdadpter;
import com.bitrubio.prototipoebitrubio.MenuLateral.Ayuda;
import com.bitrubio.prototipoebitrubio.MenuLateral.Premium;
import com.bitrubio.prototipoebitrubio.MenuLateral.Promociones;
import com.bitrubio.prototipoebitrubio.MenuLateral.Tarjeta;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 03/03/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MensajesAdadpter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Mensajes> mensajeList;
    LinearLayout lnr_agrAmigos,lnr_agrComentario;
    String TAG = getClass().getName();
    Toolbar toolbar;

    @Bind(R.id.img_fotoPerfil)
    ImageView imgfotoContacto;

    @Bind(R.id.txt_nomUsuario)
    TextView txt_nomUsuario;
    @Bind(R.id.txt_beats)
    TextView txt_beats;
    @Bind(R.id.txt_metas)
    TextView txt_metas;
    @Bind(R.id.txt_expertos)
    TextView txt_expertos;
    @Bind(R.id.txt_expertosComun)
    TextView txt_expertoComun;
    @Bind(R.id.txt_numAmigos)
    TextView txt_numAmigos;
    @Bind(R.id.txt_numAmigosComun)
    TextView txt_amigosComun;
    @Bind(R.id.txt_num_medallas)
    TextView txt_numMedallas;

    @Bind(R.id.ibtn_miperfil)
    ImageButton imgVerPerfil;


    SessionManager session;
    String idUsuario;
    Bitmap myBitmap = null;
    int varAmigo;
    String idContacto;
    Typeface tf;
    NavigationView navigationView;
    DrawerLayout drawer;
    LinearLayout lnr_premium,lnr_agenda,lnr_tarjeta,lnr_promociones,lnr_facturas,lnr_configuracion,lnr_ayuda;

    ConectaServidor servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);
        //usuario selecionado desde el recyuclerView

        servidor = new ConectaServidor();
        String UrlServer = servidor.getUrl();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            varAmigo= extras.getInt("varAmigo");
            idContacto = extras.getString("idContacto");
        }

        lnr_agrAmigos = (LinearLayout) findViewById(R.id.lnr_agrega_amgigo);
        lnr_agrComentario = (LinearLayout) findViewById(R.id.lnr_agrega_comentario);

        if(varAmigo == 1){
            lnr_agrAmigos.setVisibility(View.GONE);
            lnr_agrComentario.setVisibility(View.VISIBLE);
        }else{
            lnr_agrComentario.setVisibility(View.GONE);
            lnr_agrAmigos.setVisibility(View.VISIBLE);
        }


        Log.e(TAG,"Id_Contacto "+idContacto);
        Log.e(TAG,"varAmigo "+varAmigo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_menu_new);
        setSupportActionBar(toolbar);
        tf = Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf");
        setTexto(tf);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menuLateral();
        ImageView imagePerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imagePerfil);
        TextView nombreSession = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombreSession);
        nombreSession.setText(nameSession + " " + apeSession);
        nombreSession.setTypeface(tf);
        File fileSDcard = Environment.getExternalStorageDirectory();
        File fileFotoPerfil = new File(fileSDcard, "imagen.jpg");
        // foto perfil usuario para menu lateral
        if (fileFotoPerfil.exists()) {
            myBitmap = BitmapFactory.decodeFile(fileFotoPerfil.getAbsolutePath());
            imagePerfil.setImageBitmap(myBitmap);
        } else {
            Picasso.with(this)
                    .load(UrlServer+"fotosPerfil/"+idUsuario+".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(imagePerfil);
        }

        //  foto perfil cntacto o conocido
        Picasso.with(this).load(UrlServer+"fotosPerfil/"+idContacto+".jpg").error(R.drawable.ic_sin_foto).into(imgfotoContacto);

        imgVerPerfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ProfileActivity.this, R.style.DialogTheme);
                dialog.setContentView(R.layout.dialog_cambiar_perfil);

                TextView txtDejar = (TextView) dialog.findViewById(R.id.txt_dejar_amigo);
                TextView txtCambiar = (TextView) dialog.findViewById(R.id.txt_cambiar_parentesco);
                TextView txtReportar = (TextView) dialog.findViewById(R.id.txt_reportar);
                TextView txtBloquear = (TextView) dialog.findViewById(R.id.txt_bloquear);

                txtDejar.setTypeface(tf);
                txtCambiar.setTypeface(tf);
                txtReportar.setTypeface(tf);
                txtBloquear.setTypeface(tf);
                //dejar de serAmigo
                txtDejar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                         final Dialog dialogDejar = new Dialog(ProfileActivity.this,R.style.DialogTheme);
                        dialogDejar.setContentView(R.layout.dialog_dejar_amigo);
                        TextView txtPreguntaDejar = (TextView) dialogDejar.findViewById(R.id.txt_pregunta);
                        txtPreguntaDejar.setTypeface(tf);
                        txtPreguntaDejar.setText("¿Estas seguro que deseas dejar de ser amigo de Alan Contreras?");
                        ImageButton aceptar = (ImageButton) dialogDejar.findViewById(R.id.btn_aceptar);
                        ImageButton cancelar = (ImageButton) dialogDejar.findViewById(R.id.btn_cancel);
                        
                        aceptar.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ProfileActivity.this, "Aceptar", Toast.LENGTH_SHORT).show();
                                dialogDejar.dismiss();
                            }
                        });
                        cancelar.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ProfileActivity.this, "Cancelar", Toast.LENGTH_SHORT).show();
                                dialogDejar.dismiss();
                            }
                        });

                        dialogDejar.show();

                    }
                });

                txtReportar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent = new Intent(getBaseContext(),Reportar.class);
                        startActivity(intent);

                    }
                });

                dialog.show();

            }
        });

        mensajeList = new ArrayList<Mensajes>();
        mensajeList.add(new Mensajes(1, "Roberto Martinez", "I tried a scaleType of fitCenter and centerCrop (along with a few variations of the drawable/image for different densities) and it worked!", " 1 min","2"));
        mensajeList.add(new Mensajes(2,"Ale Gonzales", "I tried a scaleType of fitCenter and centerCrop (along with a few variations of the drawable/image for different densities) and it worked! ", "1 hr","3"));
        mensajeList.add(new Mensajes(3,"Vanessa Hernandez" ,"mensaje mensaje 3", "1.30 hr","4"));
        mensajeList.add(new Mensajes(4,"Sara Reyes" ,"I tried a scaleType of fitCenter and centerCrop (along with a few variations of the drawable/image for different densities) and it worked! ", "12/01/2016","5"));
        mensajeList.add(new Mensajes(5,"Gustavo Lopez" ,"mensaje mensaje mensaje 5", "11/01/2016","6"));
        mensajeList.add(new Mensajes(6,"Erika Garcia","mensaje mensaje mensaje 6", "10/01/2016","12"));
        mensajeList.add(new Mensajes(7,"Elizabeth Chavez", "mensaje mensaje mensaje 7", "05/01/2016","12"));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_perfil_msg);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MensajesAdadpter(mensajeList, this);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private void setTexto(Typeface tf){
        txt_nomUsuario.setTypeface(tf);
        txt_beats.setTypeface(tf);
        txt_metas.setTypeface(tf);
        txt_expertos.setTypeface(tf);
        txt_expertoComun.setTypeface(tf);
        txt_numAmigos.setTypeface(tf);
        txt_amigosComun.setTypeface(tf);
        txt_numMedallas.setTypeface(tf);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
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
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);

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
                Intent inten_premium = new Intent(ProfileActivity.this, Premium.class);
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
                Intent intent_tarjeta = new Intent(ProfileActivity.this, Tarjeta.class);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_tarjeta);
            }
        });
        lnr_promociones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent_promociones = new Intent(ProfileActivity.this, Promociones.class);
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
                Intent intent_ayuda = new Intent(ProfileActivity.this, Ayuda.class);
                intent_ayuda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_ayuda);
            }
        });
    }


}
