package com.bitrubio.prototipoebitrubio;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AsynkData.AsyncTaskFondo;
import com.bitrubio.prototipoebitrubio.Bitrubian.AlertaActvity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ComentarioActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ComunidadActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.ExpertosActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.Mensajes;
import com.bitrubio.prototipoebitrubio.Bitrubian.ServiciosActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.Entidades.Metas;
import com.bitrubio.prototipoebitrubio.MenuLateral.Ayuda;
import com.bitrubio.prototipoebitrubio.MenuLateral.Premium;
import com.bitrubio.prototipoebitrubio.MenuLateral.Promociones;
import com.bitrubio.prototipoebitrubio.MenuLateral.Tarjeta;
import com.bitrubio.prototipoebitrubio.Metas.TiempoMeta;
import com.ogaclejapan.arclayout.ArcLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Cambiafondo.CambiafondoListener,
        FragDialogSiento.ComoMesientoListener, FragDialogSiento.ComoMeSientoEmocianlListener {

    boolean dirX = true;


    String TAG = getClass().getName();
    SessionManager session;
    Toolbar toolbar;

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

    @Bind(R.id.btn_left)
    ImageButton _btnLeft;
    @Bind(R.id.btn_right)
    ImageButton _btnRight;


    ImageView imgFondo, imgFondoNueva;
    private RecyclerView mRecyclerView;
    private MensajesAdadpter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Mensajes> mensajeList;

    private ArrayList<Metas> metasList;
    HorizontalScrollView horizontalScrollView;
    NavigationView navView;

    TextView _txtSiento;
    TextView txt_porcentaje;
    TextView txt_elije_meta;
    TextView txt_mi_perfil;
    TextView txt_condiciones;
    ImageView _btncerrarBity;
    @Bind(R.id.edit_comentario)
    EditText edit_comentario;
    ImageView _image_foto, imagePerfil, _image_foto_new, _btnfoto, _btncomentario;
    ImageButton _btncompletaPerfil;
    private RelativeLayout mContainerView;

    RelativeLayout oldView, newView;

    public static String URL = "subeFoto.php";
    public byte[] byteArray;
    String ba1;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String idUsuario;
    ImageView[] bt_metas;

    LinearLayout lnr_nav_premium, lnr_nav_agenda, lnr_nav_tarjeta, lnr_nav_promociones, lnr_nav_facturas, lnr_nav_configuracion, lnr_nav_ayuda, linear_bity;
    RelativeLayout lnr_miPerfil, rl_foto;
    Toast toast = null;
    RelativeLayout rl_fondo;
    View menuLayout;
    ArcLayout arcLayout;
    View fab;
    Typeface tf;
    FragmentTransaction FT;
    Bitmap myBitmap;
    NavigationView navigationView;
    ConectaServidor servidor;
    AsyncTaskFondo subeImagenFondo;
    CountDownTimer waitTimer;

    /**/
    @SuppressLint("WrongViewCast")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        // toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_new));
        setSupportActionBar(toolbar);

        session = new SessionManager(getApplicationContext());
        servidor = new ConectaServidor();

        URL = servidor.getUrl() + URL;

        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");

        _btncerrarBity = (ImageView) findViewById(R.id.btn_cerrar_bity);

        mContainerView = (RelativeLayout) findViewById(R.id.container);
        _btncomentario = (ImageView) findViewById(R.id.img_comentario);

        oldView = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.linear_background_main, mContainerView, false);
        linear_bity = (LinearLayout) oldView.findViewById(R.id.linear_bity);
        rl_foto = (RelativeLayout) oldView.findViewById(R.id.rl_foto);
        newView = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.linear_bity_contenido, mContainerView, false);

        _txtSiento = (TextView) oldView.findViewById(R.id.txt_siento);
        _btncompletaPerfil = (ImageButton) oldView.findViewById(R.id.ibtn_miperfil);

        txt_porcentaje = (TextView) oldView.findViewById(R.id.txt_porcentaje);
        txt_elije_meta = (TextView) findViewById(R.id.elije_tu_meta);
        txt_mi_perfil = (TextView) oldView.findViewById(R.id.txt_miperfil);


        mContainerView.addView(oldView);

        _btnfoto = (ImageView) oldView.findViewById(R.id.btn_foto);
        _image_foto = (ImageView) oldView.findViewById(R.id.image_foto);
        lnr_miPerfil = (RelativeLayout) oldView.findViewById(R.id.lnr_miperfil);


        rl_fondo = (RelativeLayout) oldView.findViewById(R.id.rl_fondo);
        imgFondo = (ImageView) oldView.findViewById(R.id.img_fondo);
        imgFondoNueva = (ImageView) newView.findViewById(R.id.img_fondo_new);

        imgFondo.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.fondo_optimo, null));
        imgFondoNueva.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.fondo_optimo, null));
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String nameSession = user.get(SessionManager.KEY_NAME);
        String apeSession = user.get(SessionManager.KEY_APE);
        idUsuario = user.get(SessionManager.KEY_IDUSER);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        imagePerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imagePerfil);
        TextView nombreSession = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombreSession);
        nombreSession.setText(nameSession + " " + apeSession);

        RelativeLayout rlMiPerfil = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.rl_miPerfil);

        rlMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompletaPerfilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        txt_condiciones = (TextView) navigationView.findViewById(R.id.terminos_condiciones);

        linksApp(navigationView);

        TextView cerrarSession = (TextView) findViewById(R.id.cerrarSession);
        cerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });
        // arrayList METAS
        metasList = new ArrayList<>();
        metasList.add(new Metas(-1, R.drawable.foto_alimentos, "Quitar vicios"));
        metasList.add(new Metas(-2, R.drawable.img_medicamento, "Auto chequeo"));

        metasList.add(new Metas(1, R.drawable.ic_fisico, "Salud"));
        metasList.add(new Metas(2, R.drawable.ic_bienestar, "Bienestar"));
        metasList.add(new Metas(3, R.drawable.ic_enfermedad, "Enfermedad"));
        // arrayList MENSAJES
        mensajeList = new ArrayList<Mensajes>();
        mensajeList.add(new Mensajes(1, "Roberto Martinez", "along with a few variations of the drawable/image for different densities along with a few variations of the drawable/image for different densities along with a few variations of the drawable/image for different densities", " 1 min", "4"));
        mensajeList.add(new Mensajes(2, "Alejandro Gonzales", "I tried a scaleType of fitCenter and centerCrop ", "1 hr", "2"));
        mensajeList.add(new Mensajes(3, "Vanessa Hernandez", "mensaje mensaje 3", "1.30 hr", "6"));
        mensajeList.add(new Mensajes(4, "Sara Reyes", "I tried a scaleType ", "1 mes", "5"));
        mensajeList.add(new Mensajes(5, "Gustavo Lopez", "Lorem ipsum dolor sit amet, augue enim velit fusce vivamus, aliquam viverra a vestibulum tempus orci, pellentesque vitae luctus quis a amet. Elit elit euismod elementum. Vitae etiam amet ultricies. Lacinia nec quam lectus blandit. Leo dictum nascetur aliquam est. Nec eros lectus lacinia, proin sagittis montes suspendisse est, fuga maecenas, nulla quis sit eu. Occaecat non amet elit diam, lorem diam mauris, donec sit sodales laoreet in tellus, mattis aliquam id, adipiscing metus. Lectus dictum fusce massa morbi, vestibulum at pede sed ut id, cras viverra, libero at aenean quis eget. Id ullamcorper, ipsum eget erat felis faucibus etiam habitasse. ", "28 dias", "4"));
        mensajeList.add(new Mensajes(6, "Erik Garcia", "mensaje mensaje mensaje 6", "2 semanas", "12"));
        mensajeList.add(new Mensajes(7, "Alberto Chavez", "mensaje mensaje mensaje 7", "8 dias", "12"));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MensajesAdadpter(mensajeList, this);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        seccionBotones();
        // showEditDialog();
        //busca foto deafault
        File fileSDcard = Environment.getExternalStorageDirectory();
        File fileFotoPerfil = new File(fileSDcard, "imagen.jpg");
        final File fileFotoFondo = new File(fileSDcard, "imagenFondo.jpg");

        if (fileFotoPerfil.exists() && !fileFotoPerfil.isDirectory()) {

            myBitmap = BitmapFactory.decodeFile(fileFotoPerfil.getAbsolutePath());
            _image_foto.setImageBitmap(myBitmap);
            imagePerfil.setImageBitmap(myBitmap);


        } else {

            Picasso.with(this)
                    .load(servidor.getUrl() + "fotosPerfil/" + idUsuario + ".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(_image_foto);
            Picasso.with(this)
                    .load(servidor.getUrl() + "fotosPerfil/" + idUsuario + ".jpg")
                    .placeholder(R.drawable.ic_sin_foto)
                    .error(R.drawable.ic_sin_foto)
                    .into(imagePerfil);
        }

        if (fileFotoFondo.exists()) {
            Bitmap bitmapFondo = BitmapFactory.decodeFile(fileFotoFondo.getAbsolutePath());
            Drawable d = new BitmapDrawable(getResources(), bitmapFondo);
            imgFondo.setBackground(d);
        } else {
            imgFondo.setBackground(getResources().getDrawable(R.drawable.fondo_optimo));
        }
        //_btnfoto Perfil
        final Item[] items = {new Item("Seleccionar de tu galería", R.drawable.ic_tu_galeria),
                new Item("Captura una foto nueva", R.drawable.ic_camara_simple)
        };
//        final CharSequence[] items = {"Captura una foto nueva","Seleccionar de tu galería"};

        final ListAdapter adapter = new ArrayAdapter<Item>(
                this,
                android.R.layout.select_dialog_item,
                android.R.id.text1, items) {

            public View getView(int position, View convertView, ViewGroup parent) {
                //Use super class to create the View
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView) v.findViewById(android.R.id.text1);
                tv.setTextSize(16);
                tv.setTypeface(tf);
                tv.setTextColor(getResources().getColor(R.color.textColorPrimary));
                //Put the image on the TextView
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);
                //Add margin between image and text (support various screen densities)
                int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };
        _btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent para llamar a la Camara
                // final CharSequence[] items = {"Captura una foto nueva", "Seleccionar de tu galería", "Cancelar"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogTheme);

                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if (items[position].text.equals("Captura una foto nueva")) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);

                        } else if (items[position].text.equals("Seleccionar de tu galería")) {

                            Intent intent ;
                            if (Build.VERSION.SDK_INT < 19) {
                                // android jelly bean 4.3
                                intent = new Intent();
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                            } else {
                                // andoid 4.4 o superioir
                                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                            }
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Seleciona foto"), 2);
                        }
                    }
                });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);

            }
        });
        rl_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogTheme);

                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if (items[position].text.equals("Captura una foto nueva")) {
                            Log.e(TAG, "item-1 CLICK " + items[position]);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);

                        } else if (items[position].text.equals("Seleccionar de tu galería")) {
                            Log.e(TAG, "item-2 CLICK " + items[position]);
                            Intent intent ;
                            if (Build.VERSION.SDK_INT < 19) {
                                // android jelly bean 4.3
                               intent = new Intent();
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                            } else {
                                // andoid 4.4 o superioir
                                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                            }
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Seleciona foto"), 2);
                        }
                    }
                });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });
        // seteamos los titulos del menu lateral
        //setTitleMenu();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        //cabiamos el fondo
        RelativeLayout relative_fondoBity = (RelativeLayout) oldView.findViewById(R.id.rl_fondo);
        relative_fondoBity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                Cambiafondo dialog = new Cambiafondo();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
                dialog.show(manager, "titulo");
                //FragmentManager dialogPaisaje = getFragmentManager();
            }
        });

        lnr_nav_premium = (LinearLayout) findViewById(R.id.nav_premium);
        lnr_nav_premium.setOnClickListener(this);
        lnr_nav_agenda = (LinearLayout) findViewById(R.id.nav_agenda);
        lnr_nav_agenda.setOnClickListener(this);
        lnr_nav_tarjeta = (LinearLayout) findViewById(R.id.nav_tarjeta);
        lnr_nav_tarjeta.setOnClickListener(this);
        lnr_nav_promociones = (LinearLayout) findViewById(R.id.nav_promociones);
        lnr_nav_promociones.setOnClickListener(this);
        lnr_nav_facturas = (LinearLayout) findViewById(R.id.nav_facturas);
        lnr_nav_facturas.setOnClickListener(this);
        lnr_nav_configuracion = (LinearLayout) findViewById(R.id.nav_configuracion);
        lnr_nav_configuracion.setOnClickListener(this);
        lnr_nav_ayuda = (LinearLayout) findViewById(R.id.nav_ayuda);
        lnr_nav_ayuda.setOnClickListener(this);
        txt_condiciones = (TextView) findViewById(R.id.terminos_condiciones);
        txt_condiciones.setOnClickListener(this);
        _btnHome.setBackground(getResources().getDrawable(R.drawable.ic_yo_b));

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_metas);

        _btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalScrollView.scrollTo(0, 0);
            }
        });
        _btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalScrollView.scrollTo(300, 0);
            }
        });

        waitTimer = new CountDownTimer(50000, 4000) {
            @Override
            public void onTick(long millisUntilFinished) {

                turn();
            }

            @Override
            public void onFinish() {


            }
        }.start();

    }


    public void linksApp(NavigationView nvgView) {

        ImageView imgFacebook = (ImageView) nvgView.findViewById(R.id.img_facebook);
        ImageView imgTwitter = (ImageView) nvgView.findViewById(R.id.img_twitter);
        ImageView imgYoutube = (ImageView) nvgView.findViewById(R.id.img_youtube);
        ImageView imgLinkedin = (ImageView) nvgView.findViewById(R.id.img_linkedin);
        ImageView imgInstagram = (ImageView) nvgView.findViewById(R.id.img_instagram);

        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        imgYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.instagram.com/_u/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.setPackage("com.instagram.android");
                startActivity(intent);
            }
        });
        imgLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.nav_premium:
                Intent intent_prueba = new Intent(MainActivity.this, Premium.class);
                intent_prueba.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent_prueba.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent_prueba);
                //Your Operation
                break;
            case R.id.nav_agenda:
                Toast.makeText(getBaseContext(), "Agenda", Toast.LENGTH_LONG).show();
                Intent intentAgenda = new Intent(MainActivity.this, TiempoMeta.class);
                startActivity(intentAgenda);
                //Your Operation
                break;
            case R.id.nav_tarjeta:
                Intent intent_tarjeta = new Intent(MainActivity.this, Tarjeta.class);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent_tarjeta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_tarjeta);
//                Toast.makeText(getBaseContext(), "Tarjeta", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_promociones:
                Intent intent_promo = new Intent(MainActivity.this, Promociones.class);
                intent_promo.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_promo);
                //Toast.makeText(getBaseContext(), "Promociones", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_facturas:
                Toast.makeText(getBaseContext(), "Facturas", Toast.LENGTH_LONG).show();
                //Your Operation
                break;
            case R.id.nav_configuracion:
                Intent intent = new Intent(MainActivity.this, SignupVideo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                //Your Operation
                break;
            case R.id.nav_ayuda:
                // Toast.makeText(getBaseContext(), "Ayuda", Toast.LENGTH_LONG).show();
                Intent intent_ayuda = new Intent(MainActivity.this, Ayuda.class);
                intent_ayuda.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_ayuda);
                //Your Operation
                break;

            case R.id.terminos_condiciones:
                showTerminos();
                break;
            case R.id.img_facebook:

                break;
        }

        for (int metas = 0; metas < metasList.size(); metas++) {
            if (metasList.get(metas).getId() > 0) {
                if (v.getId() == metasList.get(metas).getId()) {

                    if (metasList.get(metas).getTitulo().equals("Salud")) {

                        Intent intent = new Intent(MainActivity.this, MetaFisica.class);

                        startActivity(intent);

                    } else if (metasList.get(metas).getTitulo().equals("Bienestar")) {
                        //  colortransparencia = "naranja";
                        //  showOverLay(colortransparencia);
                        Intent intent = new Intent(MainActivity.this, MetaSocial.class);

                        startActivity(intent);
                    } else if (metasList.get(metas).getTitulo().equals("Enfermedad")) {
                        //colortransparencia = "azul";
                        // showOverLay(colortransparencia);
                        Intent intent = new Intent(MainActivity.this, MetaEnfermedad.class);

                        startActivity(intent);
                    }
                }
            }
            if (metasList.get(metas).getId() < 0) {
                if (v.getId() != metasList.get(metas).getId()) {

                    if (v.getId() == -1) {

                        Intent intent = new Intent(MainActivity.this, MetaFisica.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("variable", "1");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        }
    }

    public void showTerminos() {
        Bundle args = new Bundle();
        android.app.FragmentManager fm = getFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
        args.putInt("value", 1);
        dialogFragment.setArguments(args);
        dialogFragment.show(fm, "Terminos y politica de privacidad");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                guardarImagen(thumbnail);
                guardarImagenLocal(thumbnail);
                _image_foto.setImageBitmap(thumbnail);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {


                _image_foto.setImageURI(data.getData());
                //utilizamos el atrbuti tag para almacenar la uri al archivo seleccionado
                _image_foto.setTag(data.getData());
                Bitmap imagen = ((BitmapDrawable) _image_foto.getDrawable()).getBitmap();
                //guardar imagen en el servidor
                guardarImagen(imagen);
                guardarImagenLocal(imagen);
            }

        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {


                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                guardarImagenFondo(thumbnail);
                imgFondo.setImageBitmap(thumbnail);
                imgFondoNueva.setImageBitmap(thumbnail);

            }
        }
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {

                imgFondo.setImageURI(data.getData());
                //utilizamos el atrbuti tag para almacenar la uri al archivo seleccionado
                imgFondo.setTag(data.getData());
                Bitmap imagen = ((BitmapDrawable) imgFondo.getDrawable()).getBitmap();
                guardarImagenFondo(imagen);
                Drawable dr = new BitmapDrawable(imagen);
                imgFondo.setBackgroundDrawable(dr);
                imgFondoNueva.setBackgroundDrawable(dr);

            }

        }


    }

    private String guardarImagenFondo(Bitmap imagen) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byteArray = stream.toByteArray();
        File destination = new File(Environment.getExternalStorageDirectory(), "imagenFondo.jpg");
        FileOutputStream outputStream;
        try {
            destination.createNewFile();
            outputStream = new FileOutputStream(destination);
            outputStream.write(byteArray);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private String guardarImagen(Bitmap imagen) {
        //Guarda la imagen en  el servidor
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byteArray = stream.toByteArray();
        ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        subeImagenFondo = new AsyncTaskFondo(getBaseContext(), URL, ba1, idUsuario);
        // new uploadToServer().execute();

        return null;
    }

    private void guardarImagenLocal(Bitmap imagen) {
        // gurda la imagen en Sdcard local
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(), "imagen.jpg");
        FileOutputStream fo;
        try {
            //guardamos la imagen en el telefono
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FragDialogSiento dialogsiento = FragDialogSiento.newInstance("Title");
        dialogsiento.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
        dialogsiento.show(fm, "hoy me siento");
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
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);
            addItem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        // Agregamos una capa al layout
        _image_foto_new = (ImageView) newView.findViewById(R.id.image_foto_new);
        _image_foto_new.setImageBitmap(myBitmap);

        mContainerView.removeView(oldView);
        newView.findViewById(R.id.linear_bity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContainerView.removeView(newView);
                findViewById(R.id.action_helpBeety).setVisibility(View.VISIBLE);
                imgFondoNueva.setBackground(null);
                mContainerView.addView(oldView);



                newView.setOnTouchListener( new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.d(TAG, "onTouch  " + "");
                      /*  if (newView!=null) {
                            mContainerView.removeView(newView);
                            return true;
                        }*/
                        return false;
                    }
                });

      /*          RelativeLayout rl_bity = (RelativeLayout) newView.findViewById(R.id.rl_belowBity);
                rl_bity.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        mContainerView.removeView(newView);
                        findViewById(R.id.action_helpBeety).setVisibility(View.VISIBLE);
                        imgFondoNueva.setBackground(null);
                        mContainerView.addView(oldView);
                    }
                });*/
            }
        });

        mContainerView.addView(newView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void seccionBotones() {

        bt_metas = new ImageView[metasList.size()];
        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        _btnComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Comunidad", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ComunidadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        _btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Alerta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AlertaActvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        _btnExpertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Expertos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ExpertosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        _btnServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiciosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //  Toast.makeText(getBaseContext(), "Servicios", Toast.LENGTH_SHORT).show();

            }
        });
        _txtSiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showEditDialog();
            }
        });
        lnr_miPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this, R.style.DialogTheme);
                dialog.setContentView(R.layout.dialog_confirmar_contra);

                final EditText editContraseña = (EditText) dialog.findViewById(R.id.edit_confirmaContraseña);

                editContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                final CheckBox checkVer = (CheckBox) dialog.findViewById(R.id.chk_verPass);

                checkVer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!isChecked) {
                            //Toast.makeText(MainActivity.this, "chek", Toast.LENGTH_SHORT).show();
                            editContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            // Toast.makeText(MainActivity.this, "nochek", Toast.LENGTH_SHORT).show();
                            editContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                    }
                });


                ImageButton btnConfirmar = (ImageButton) dialog.findViewById(R.id.btn_confirmarContraseña);

                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                    String confirmarContraseña = null;

                    @Override
                    public void onClick(View v) {


                        confirmarContraseña = editContraseña.getText().toString();

                        if (confirmarContraseña.equals("")) {
                            Intent intent = new Intent(MainActivity.this, CompletaPerfilActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();

            }
        });

        edit_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComentarioActivity.class);
                startActivity(intent);
            }
        });
        _btncomentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComentarioActivity.class);
                startActivity(intent);
            }
        });

        if (metasList.size() > 0) {
            ViewGroup linearLayout = (ViewGroup) findViewById(R.id.linear_metas);

            for (int metas = 0; metas < metasList.size(); metas++) {

                LinearLayout childLayout = new LinearLayout(this);
                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, getResources().getDisplayMetrics()); // margin left para el linearLayout
                int marginRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()); // margin right  para el linearLayout

                linearParams.setMargins(marginLeft, 0, marginRight, 0);

                childLayout.setLayoutParams(linearParams);
                childLayout.setGravity(Gravity.CENTER);
                childLayout.setOrientation(VERTICAL);


                bt_metas[metas] = new de.hdodenhof.circleimageview.CircleImageView(this);
                int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());

                bt_metas[metas].setLayoutParams(new LinearLayout.LayoutParams(width, height, Gravity.CENTER_HORIZONTAL));

                bt_metas[metas].setImageResource(metasList.get(metas).getImagen());

                bt_metas[metas].setId(metasList.get(metas).getId());
                bt_metas[metas].setOnClickListener(this);

                TextView mValue = new TextView(this);
                mValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                mValue.setTextSize(14);
                mValue.setTypeface(tf);
                mValue.setGravity(Gravity.BOTTOM | Gravity.CENTER);
                mValue.setTextColor(getResources().getColor(R.color.textColorPrimary));

                mValue.setText(metasList.get(metas).getTitulo().toString());

                childLayout.addView(mValue, 0);
                childLayout.addView(bt_metas[metas], 0);
                linearLayout.addView(childLayout);
            }

        }
    }

    @Override
    public void setBackgroundFondo(int int_drawable) {
        imgFondo.setBackgroundDrawable(getResources().getDrawable(int_drawable));
    }

    //Boton como me siento //
    @Override
    public void setComomesiento(int comoMeSiento) {
        String sientoFisico = "Físicamente";
        int imgDrawable = 0;
        if (comoMeSiento == 0) {

            imgDrawable = R.drawable.ic_mal_blanco;
        } else if (comoMeSiento == 1) {

            imgDrawable = R.drawable.ic_regular_blanco;
        } else if (comoMeSiento == 2) {

            imgDrawable = R.drawable.ic_bien_blanco;
        }

        _txtSiento.setCompoundDrawablesWithIntrinsicBounds(0, 0, imgDrawable, 0);
        _txtSiento.setCompoundDrawablePadding(1);
        _txtSiento.setText(sientoFisico);


    }

    @Override
    public void setComomeSientoEmocional(int emocional) {
        String sientoEmcoional = "Emocionalmente";
        int imgDrawable = 0;
        if (emocional == 0) {
            imgDrawable = R.drawable.ic_mal_blanco;
        } else if (emocional == 1) {
            imgDrawable = R.drawable.ic_regular_blanco;
        } else if (emocional == 2) {
            imgDrawable = R.drawable.ic_bien_blanco;
        }
        _txtSiento.setCompoundDrawablesWithIntrinsicBounds(0, 0, imgDrawable, 0);
        _txtSiento.setCompoundDrawablePadding(1);
        _txtSiento.setText(sientoEmcoional);


    }

    public void turn() {
        if (dirX = true) {
            dirX = false;

            ObjectAnimator flip = ObjectAnimator.ofFloat(_txtSiento, "rotationX", 0f, 360f);
            flip.setDuration(1300);
            flip.start();
        } else {
            dirX = true;

            ObjectAnimator flip = ObjectAnimator.ofFloat(_txtSiento, "rotationX", 360f, 0f);
            flip.setDuration(1300);
            flip.start();
        }

    }


    /// swipe recyclerView ///
    void refreshItems() {

        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
