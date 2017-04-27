package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Mario on 17/12/2015.
 * pantalla con el video de incio y presentacion
 */
public class IntroActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static String TAG = "IntroActivity";

    @Bind(R.id.btn_logeo)
    Button _logeoBtn;
    @Bind(R.id.btn_registro)
    Button _registroBtn;
    @Bind(R.id.tv_Terminos)
    TextView tvTerminos;


    @Bind(R.id.video_in)
    VideoView mVideoView;
    private Uri uri;

     SessionManager session;
    private ProgressDialog progressDialog;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
       // session.checkLogin();
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        HashMap<String, String> user = session.getUserDetails();
        String bandera = user.get(SessionManager.KEY_SUCESS);
        tvTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Bundle args = new  Bundle();
                    android.app.FragmentManager fm = getFragmentManager();
                    DialogTerminosCondiciones dialogFragment = new DialogTerminosCondiciones();
                    dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogTheme);
                    args.putInt("value",1);
                    dialogFragment.setArguments(args);
                    dialogFragment.show(fm, "Terminos y politica de privacidad");
            }
        });

        if(bandera!=null){
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            _logeoBtn.setTypeface(tf);
            _registroBtn.setTypeface(tf);

            _logeoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            _registroBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(IntroActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            });


        /*Loop de video*/
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    mp.setLooping(true);
                }
            });
            guardarConfiguracion();

        }
    }
    public void videoback(){

            uri = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.bitrubio_doc); //do not add any extension
            mVideoView = (VideoView) findViewById(R.id.video_in);
            mVideoView.setVideoURI(uri);
            mVideoView.requestFocus();
            mVideoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              videoback();
            }
        },0);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
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

    /**
     * si el video ya fue visto guardamo el estatus
     */
    public void guardarConfiguracion()
    {
        SharedPreferences preferencias = getSharedPreferences("Login_User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("var_video", "1");
        editor.commit();

    }



}
