package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AsynkData.ServiceHandler;
import com.bitrubio.prototipoebitrubio.Entidades.Bitrubian;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Mario on 16/12/2015.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String URL = "loguin.php";
    ArrayList<NameValuePair> data;
    private ProgressDialog progressDialog;
    SessionManager session;

    private int intMedico = 0;

    @Bind(R.id.input_email)
    EditText _emailText;

    @Bind(R.id.input_password)
    EditText _passwordText;

    @Bind(R.id.btn_login)
    ImageView _loginButton;

    @Bind(R.id.link_medico)
    TextView _linkMedico;


    @Bind(R.id.link_signup)
    TextView _signupLink;

    @Bind(R.id.link_entrar)
    TextView _linkEntrar;

    private String email;
    private String password;
    private String _userBD;
    private String _iduserDB;
    private String _nombreBD;
    private String _apePatBD;
    private String _mailDB;
    private String _successDB = "";
    private ArrayList<Bitrubian> userData;
    LinearLayout lnr_textura;
    ConectaServidor servidor;
    Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        servidor = new ConectaServidor();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.iniciaLogin));
        mTitle.setTextSize(16);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lnr_textura = (LinearLayout) findViewById(R.id.lnr_textura);
        Bitmap bit_textura = null ;
        bit_textura = redimensionarImagenMaximo(drawableToBitmap(getResources().getDrawable(R.drawable.textura)),90,90);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bit_textura);
        lnr_textura.setBackground(ob);


        lnr_textura.setBackground(getResources().getDrawable(R.drawable.textura));

            //getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        ButterKnife.bind(this);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/avenir-light.ttf");
        _emailText.setTypeface(tf);
        _passwordText.setTypeface(tf);
        _linkMedico.setTypeface(tf);
        _linkEntrar.setTypeface(tf);
        _signupLink.setTypeface(tf);

        userData = new ArrayList<Bitrubian>();
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);*/
            }
        });
    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth) {
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }
    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        new LoginUser().execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    public void onLoginFailed() {
        _loginButton.setEnabled(true);

         Toast.makeText(getBaseContext(), "Logeo incorrecto", Toast.LENGTH_SHORT).show();
        //this.finish();
    }

    public boolean validate() {
        boolean valid = true;
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ingrese un correo valido");
            valid = false;

        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _passwordText.setError("debe contener 6 caracteres");

            valid = false;

        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void DatosUsuario() {

        // podemos usar estos valores como session dentro de la aplicacion
        for (int i = 0; i < userData.size(); i++) {
            _nombreBD = userData.get(i).getNombre().toString();
            _apePatBD = userData.get(i).getApe_pat().toString();
            _mailDB = userData.get(i).getMail().toString();
            _successDB = userData.get(i).getSuccess().toString();
            _iduserDB = userData.get(i).getIdUsuario().toString();

        }

        //Log.e(TAG, "sucess :" + _successDB);

        if (_successDB.equals("1")) {

            session.createLoginSession(_nombreBD, _apePatBD, _mailDB, _successDB,_iduserDB);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        } else if (_successDB.equals("2")) {
            Toast.makeText(getBaseContext(), "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
        } else if (_successDB.equals("3")) {
            Toast.makeText(getBaseContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
        }

    }

    class LoginUser extends AsyncTask<Void, Void, Void> {
        String Json;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.DialogTheme);

            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Validando Datos...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                DatosUsuario();
            }else{
                Toast.makeText(getBaseContext(), "Sin coneccion", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("email", email));
            data.add(new BasicNameValuePair("password", password));
            //  Agregamos el servicio para la conexion por http
            ServiceHandler jsonPArser = new ServiceHandler();
            // mandamos los datos por metodo post
            Json = jsonPArser.makeServiceCall(servidor.getUrl()+URL, ServiceHandler.POST, data);

            try {
                JSONObject jsonObject = new JSONObject(Json);
                JSONArray datosUser = jsonObject.getJSONArray("login");

                for (int i = 0; i < datosUser.length(); i++) {
                    JSONObject datosObj = datosUser.getJSONObject(i);
                    String error = datosObj.getString("success");
                    if (error.equals("2") ) {
                        userData.add(new Bitrubian(0,"0", "0", "0", "2", "0"));
                    }else if(error.equals("3")){
                        userData.add(new Bitrubian(0,"0", "0", "0", "3", "0"));
                    }else {

                        userData.add(new Bitrubian(
                                datosObj.getInt("idbitrubian"),
                                datosObj.getString("nombre"),
                                datosObj.getString("apellido_pat"),
                                datosObj.getString("correo"),
                                datosObj.getString("success"),
                                datosObj.getString("idbitrubian"))
                        );
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            return null;
        }
    }
}