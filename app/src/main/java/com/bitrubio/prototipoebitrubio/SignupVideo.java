package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bitrubio.prototipoebitrubio.Entidades.DatosPerfilManager;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mario on 08/01/2016.
 * carga el video de introiducioon ala aplicacion
 */
public class SignupVideo extends Activity implements SurfaceHolder.Callback {
    private String TAG = "VideoSignup";
    @Bind(R.id.videoview)
    VideoView mVideoView;
    private String uriPath, nombre_user, comentario_bienvenida,medium,low,sbitrubio;
    private Uri uri;
    TextView  txt_bienvendida,txt_medium,txt_low,txt_bitrubio;
    ImageView img_repetir,btnSiguiente;
    long duration = 0;
    int current = 0;
    DatosPerfilManager perfilManager;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_signup);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        ButterKnife.bind(this);
        inicializaVideo();
        btnSiguiente = (ImageView) findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupVideo.this, SignupElige.class);
                startActivity(intent);
            }
        });
        perfilManager = new DatosPerfilManager(this);
        HashMap<String, String> datos = perfilManager.getDatosPerfil();
        Log.e(TAG,"datos");
        String nombre_db = datos.get(perfilManager.NOMBRE);
        String apepat_db = datos.get(perfilManager.FIRST_APE);
        String sexo = datos.get(perfilManager.SEXO);
        Log.e(TAG,"sexo"+sexo);
        String txtBienvenida=null;
        if (sexo!= null) {
            if (sexo.equals("H")) {
                txtBienvenida = "Bienvenido";
            } else {
                txtBienvenida = "Bienvenida";
            }
        }

        comentario_bienvenida = "¡"+txtBienvenida+" "+ nombre_db +  "!";
        medium = "Conoce todo lo que puedes";
        low = "hacer con";
        sbitrubio = "bitrubio";

        txt_bienvendida = (TextView) findViewById(R.id.txt_bienvenida);
        txt_medium = (TextView) findViewById(R.id.txt_medium);
        txt_low = (TextView) findViewById(R.id.txt_low);
        txt_bitrubio = (TextView) findViewById(R.id.txt_bitrubio);

        txt_bienvendida.setText(comentario_bienvenida);
        txt_medium.setText(medium);
        txt_low.setText(low);
        txt_bitrubio.setText(" "+sbitrubio);

        img_repetir = (ImageView) findViewById(R.id.img_repetir);
        img_repetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializaVideo();

            }
        });
        img_repetir.setVisibility(View.GONE);

    }

    /**
     * inicializa el video
     */
    public void inicializaVideo() {
        uriPath = "android.resource://com.bitrubio.prototipoebitrubio/" + R.raw.bitrubio_family;
        uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                img_repetir.setVisibility(View.GONE);
                duration = mVideoView.getDuration();

                new CountDownTimer(duration, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        img_repetir.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializaVideo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
