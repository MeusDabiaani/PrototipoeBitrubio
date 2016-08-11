package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AsynkData.ServiceHandler;
import com.bitrubio.prototipoebitrubio.Bitrubian.Bitrubian;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.Entidades.DatosPerfilManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Mario on 17/12/2015.
 */
public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    //private String URL = "http://www.meustech.com:8080/bitrubio/movil/registro.php";
    private String URL = "registro.php";
    private int success;
    private String TAG_SUCCESS = "success";
    // captura de campos
    EditText input_name;
    EditText input_apePat;
    EditText input_fechaNac;

    EditText input_celular;
    EditText input_email;
    EditText input_password;
    EditText input_confirmarpass;
    EditText input_codAmigo;
    CheckBox _checkOrganos;
    CheckBox _checkSangre;
    CheckBox _checkNo;
    CheckBox _checkCondiciones;
    TextView _txtCondiciones, _txtDonar;
    ImageView img_contraseña, img_info;
    View viewConfirmar;
    // solo para estilo de letras


    ImageButton FAB_DONE, FAB_CANCEL;

    String nombre;
    String ape_pat;
    String sexo;
    String fecha;
    String celular;
    String mail;
    String contrasena;
    String confirmarPass;
    String codigo;
    int year_new,year_old;

    int sangre, organos, condiciones, donar;

    ProgressDialog pDialog;
    ArrayList<NameValuePair> datosPost;
    SessionManager session;
    private ArrayList<Bitrubian> userData;
    private String _iduserDB;
    private String _nombreBD;
    private String _apePatBD;
    private String _apeMatBD = "";
    private String _mailDB;
    String fecha_nacimiento="";
    Toolbar toolbar;
    Calendar calender;

    DatosPerfilManager perfilManager;
    Typeface tf;
    RadioGroup rdGroup;

    @Bind(R.id.line_nombre)
    View lineNombre;
    ConectaServidor servidor;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        servidor = new ConectaServidor();
        URL = servidor.getUrl()+URL;
        Log.e(TAG,"url"+URL);
        perfilManager = new DatosPerfilManager(this);
        // seteamos la tipografia
        tf= Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf");
        //  ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.iniciaRegistro));
        mTitle.setTextSize(16);

        userData = new ArrayList<Bitrubian>();
        //getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        final LinearLayout layout = (LinearLayout) findViewById(R.id.formulario);

        input_name = (EditText) findViewById(R.id.input_name);
        input_apePat = (EditText) findViewById(R.id.input_apePat);
        input_fechaNac = (EditText) findViewById(R.id.input_fechaNac);
        input_celular = (EditText) findViewById(R.id.input_celular);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        input_codAmigo = (EditText) findViewById(R.id.input_codAmigo);
        input_confirmarpass = (EditText) findViewById(R.id.input_confirmarpass);
        _txtCondiciones = (TextView) findViewById(R.id.textCondiciones);
        _txtDonar = (TextView) findViewById(R.id.textDonar);
        img_contraseña = (ImageView) findViewById(R.id.img_contraseña);
        img_info = (ImageView) findViewById(R.id.img_info);
        viewConfirmar = (View) findViewById(R.id.view_confirmacion);

        rdGroup = (RadioGroup) findViewById(R.id.radio_group);

        mTitle.setTypeface(tf);
        input_name.setTypeface(tf);
        input_apePat.setTypeface(tf);
        input_fechaNac.setTypeface(tf);
        input_celular.setTypeface(tf);
        input_email.setTypeface(tf);
        input_password.setTypeface(tf);
        input_codAmigo.setTypeface(tf);
        input_confirmarpass.setTypeface(tf);
        _txtCondiciones.setTypeface(tf);
        _txtDonar.setTypeface(tf);

        input_confirmarpass.setVisibility(View.GONE);
        img_contraseña.setVisibility(View.GONE);
        viewConfirmar.setVisibility(View.GONE);
        // controlamos las opciones del Sexo para las personas
        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(fm, "Terminos y politica de privacidad");
            }
        });

        calender = Calendar.getInstance();
        final CheckBox rd_mujer, rd_hombre;
        rd_hombre = (CheckBox) findViewById(R.id.rad_hombre);
        rd_hombre.setTypeface(tf);
        rd_mujer = (CheckBox) findViewById(R.id.rad_mujer);
        rd_mujer.setTypeface(tf);

        rd_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();

                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sexo = "H";
                    rd_mujer.setChecked(false);
                    hideKeyboard(view);
                } else {
                    // Toast.makeText(getBaseContext(), "No donar Sangre", Toast.LENGTH_LONG).show();
                    sexo = "M";
                    rd_hombre.setChecked(true);
                }
            }
        });
        rd_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();

                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sexo = "M";
                    rd_hombre.setChecked(false);
                    hideKeyboard(view);
                } else {
                    // Toast.makeText(getBaseContext(), "No donar Sangre", Toast.LENGTH_LONG).show();
                    sexo = "H";
                    rd_mujer.setChecked(true);
                }
            }
        });

        //<editor-fold desc="Checkbox Sangre">
        _checkSangre = (CheckBox) findViewById(R.id.checkboxSangre);
        _checkSangre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();

                if (isChecked) {
                    //   Toast.makeText(getBaseContext(), "Donar Sangre", Toast.LENGTH_LONG).show();
                    sangre = 1;
                    _checkNo.setChecked(false);
                    hideKeyboard(view);
                } else {
                    // Toast.makeText(getBaseContext(), "No donar Sangre", Toast.LENGTH_LONG).show();
                    sangre = 0;
                    if (_checkSangre.isChecked()== false && _checkOrganos.isChecked() == false){
                        _checkNo.setChecked(true);
                    }
                }
            }
        });


        //</editor-fold>
        //<editor-fold desc="CheckBox Organos">
        _checkOrganos = (CheckBox) findViewById(R.id.checkboxOrganos);
        _checkOrganos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked) {
                    // Toast.makeText(getBaseContext(), "Donar Oganos", Toast.LENGTH_LONG).show();
                    organos = 1;
                    _checkNo.setChecked(false);
                    hideKeyboard(view);
                } else {
                    //Toast.makeText(getBaseContext(), "No donar Organos", Toast.LENGTH_LONG).show();
                    organos = 0;
                    if (_checkSangre.isChecked()== false && _checkOrganos.isChecked() == false){
                        _checkNo.setChecked(true);
                    }
                }
            }
        });


        //</editor-fold>
        //<editor-fold desc="Checkbox No">
        _checkNo = (CheckBox) findViewById(R.id.checkboxNo);

        _checkNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked) {
                    //     Toast.makeText(getBaseContext(), "No donar", Toast.LENGTH_LONG).show();
                    donar = 0;
                    _checkSangre.setChecked(false);
                    _checkOrganos.setChecked(false);

                    hideKeyboard(view);
                } else {
                    //    Toast.makeText(getBaseContext(), "Si deseo donar", Toast.LENGTH_LONG).show();
                    donar = 1;
                    if (_checkOrganos.isChecked()==false && _checkSangre.isChecked()==false ){
                        _checkNo.setChecked(true);
                    }
                }
            }
        });

        //</editor-fold>
        //<editor-fold desc="Checkbox Condiciones">
        _checkCondiciones = (CheckBox) findViewById(R.id.checkboxCondiciones);
        _checkCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    condiciones = 1;
                } else {
                    condiciones = 0;
                }
            }
        });

        _checkNo.setTypeface(tf);
        _checkOrganos.setTypeface(tf);
        _checkSangre.setTypeface(tf);
        //</editor-fold>
        //<editor-fold desc="DatePiker Fecha">
        // mostramos el calendario al tocar el input
        input_fechaNac.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showDatePicker();
                    return true;
                }
                return false;
            }
        });
        input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                showConfirmacion();
            }
        });
        img_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showinfoAfilacion();
            }
        });
        _txtCondiciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTerminos();
            }
        });
        //</editor-fold>
        FAB_DONE = (ImageButton) findViewById(R.id.btn_signup);
        FAB_DONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = input_name.getText().toString();
                ape_pat = input_apePat.getText().toString();
                fecha = input_fechaNac.getText().toString();
                celular = input_celular.getText().toString();
                mail = input_email.getText().toString();
                contrasena = input_password.getText().toString();
                codigo = input_codAmigo.getText().toString();
                confirmarPass = input_confirmarpass.getText().toString();
          /*      Intent i  = new Intent(SignupActivity.this, SignupVideo.class);
                startActivity(i);*/
                if (validate() == true) {
                    new SetBitrubian().execute();
                }
            }
        });
        FAB_CANCEL = (ImageButton) findViewById(R.id.btn_cancel);
        FAB_CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, IntroActivity.class);
                startActivity(intent);

            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });

    }



    // muestra el el fragmento con el calendario
    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");



    }
    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    // muestra el el input confirma contraseña
    private void showConfirmacion() {
        img_contraseña.setVisibility(View.VISIBLE);
        input_confirmarpass.setVisibility(View.VISIBLE);
        viewConfirmar.setVisibility(View.VISIBLE);


    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String mes = null;
            if (monthOfYear + 1 == 1) {
                mes = "ene";
            }
            if (monthOfYear + 1 == 2) {
                mes = "feb";
            }
            if (monthOfYear + 1 == 3) {
                mes = "mar";
            }
            if (monthOfYear + 1 == 4) {
                mes = "abr";
            }
            if (monthOfYear + 1 == 5) {
                mes = "may";
            }
            if (monthOfYear + 1 == 6) {
                mes = "jun";
            }
            if (monthOfYear + 1 == 7) {
                mes = "jul";
            }
            if (monthOfYear + 1 == 8) {
                mes = "ago";
            }
            if (monthOfYear + 1 == 9) {
                mes = "sep";
            }
            if (monthOfYear + 1 == 10) {
                mes = "oct";
            }
            if (monthOfYear + 1 == 11) {
                mes = "nov";
            }
            if (monthOfYear + 1 == 12) {
                mes = "dic";
            }
            year_new = year;
            input_fechaNac.setText(String.valueOf(dayOfMonth) + "/" + mes + "/" + String.valueOf(year));
             //fecha_nacimiento = String.valueOf(year) + "-" + monthOfYear + 1 + "-" + String.valueOf(dayOfMonth);


        }
    };



    private void resultSetBitrubian() {
        for (int i = 0; i < userData.size(); i++) {
            _nombreBD = userData.get(i).getNombre().toString();
            _apePatBD = userData.get(i).getApe_pat().toString();
            _mailDB = userData.get(i).getMail().toString();
            success = Integer.parseInt(userData.get(i).getSuccess().toString());
            _iduserDB = userData.get(i).getIdUsuario().toString();
            /*Log.e(TAG, "result for succes " + success);*/
        }
        if (success == 1) {
            //  Toast.makeText(this, "Bitrubian Creado correctamente", Toast.LENGTH_SHORT).show();
            perfilManager.insertDatosA(nombre, _apePatBD, _apeMatBD, sexo, fecha,
                    celular, _mailDB, contrasena, String.valueOf(donar), String.valueOf(sangre), String.valueOf(organos));
            session.createLoginSession(_nombreBD, _apePatBD, _mailDB, String.valueOf(success), _iduserDB);
            Intent i = new Intent(SignupActivity.this, SignupVideo.class);
            startActivity(i);
        } else if (success == 2) {
            Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
        } else if (success == 3) {
            Toast.makeText(this, "Telefono ya registrado", Toast.LENGTH_SHORT).show();
        }else {
            Intent i = new Intent(SignupActivity.this, IntroActivity.class);
            startActivity(i);
        }
    }

    class SetBitrubian extends AsyncTask<String, String, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SignupActivity.this);
            pDialog.setMessage("Creando usuario");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            datosPost = new ArrayList<NameValuePair>();
            datosPost.add(new BasicNameValuePair("nombre", nombre));
            datosPost.add(new BasicNameValuePair("apellido_pat", ape_pat));
            datosPost.add(new BasicNameValuePair("fecha_nac", fecha));
            datosPost.add(new BasicNameValuePair("sexo", sexo));
            datosPost.add(new BasicNameValuePair("celular", celular));
            datosPost.add(new BasicNameValuePair("email", mail));
            datosPost.add(new BasicNameValuePair("password", contrasena));
            datosPost.add(new BasicNameValuePair("sangre", String.valueOf(sangre)));
            datosPost.add(new BasicNameValuePair("organos", String.valueOf(organos)));
            datosPost.add(new BasicNameValuePair("terminos", String.valueOf(condiciones)));
            datosPost.add(new BasicNameValuePair("codigoAmigo", codigo));
            ServiceHandler jsonParser = new ServiceHandler();
            String json = jsonParser.makeServiceCall(URL, ServiceHandler.POST, datosPost);

            try {
                JSONObject Obj = new JSONObject(json);
                Log.e(TAG, "Obj " + Obj);
                JSONArray datosUser = Obj.getJSONArray("registro");
                Log.e(TAG, "datosUser " + datosUser);

                for (int i = 0; i < datosUser.length(); i++) {
                    JSONObject datosObj = datosUser.getJSONObject(i);
                    String error = datosObj.getString("success");
                    Log.e(TAG, "error success" + error);
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
            return success;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
                resultSetBitrubian();
            }
        }
    }

    public boolean validate() {
        boolean valid = true;
        contrasena = input_password.getText().toString();
        confirmarPass = input_confirmarpass.getText().toString();
        if (nombre.equals("") && ape_pat.equals("") && fecha.equals("") && celular.equals("") &&
                mail.equals("") && contrasena.equals("") && confirmarPass.equals("")) {
            input_name.requestFocus();
           // mySetLineError(lineNombre);
            mySetError(input_name);
            mySetError(input_apePat);
            mySetError(input_fechaNac);
            mySetError(input_celular);
            mySetError(input_email);

           /* input_name.setError("Ingrese su nombre");*/
            valid = false;
        } else {
            input_name.setError(null);
            input_apePat.setError(null);
            input_fechaNac.setError(null);
            input_celular.setError(null);
            input_email.setError(null);
            input_confirmarpass.setError(null);
            input_password.setError(null);
        }
        if (contrasena.length() < 6) {

            mySetError(input_password);
            valid = false;
        } else if (!contrasena.equals(confirmarPass)) {

            mySetError(input_confirmarpass);
            valid = false;
        }
        if (celular.length() < 10) {

            mySetError(input_celular);
            valid = false;
        } else {
            input_celular.setError(null);
        }
        if (mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            mySetError(input_email);
            valid = false;
        } else {
            input_email.setError(null);

        }
        if (_checkCondiciones.isChecked()==false){
            mySetErrorCheck();
            valid = false;
        }

        year_old = calender.get(Calendar.YEAR);
        int diferencia = year_old-year_new;

        if(diferencia < 18){
            showInfoMenor();
            valid = false;
        }
        return valid;
    }

    private void showInfoMenor() {

        input_fechaNac.setText("Debes ser mayor de edad");
        input_fechaNac.setTextColor(getResources().getColor(R.color.editError700));
        new AlertDialog.Builder(this,R.style.DialogTheme)
                .setTitle("Aviso")
                .setMessage("Debes ser mayor de edad")
                .setCancelable(false)
                .setPositiveButton("Entendido",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                              /*  Toast.makeText(getApplicationContext(),
                                        "You clicked on YES", Toast.LENGTH_SHORT)
                                        .show();*/
                            }
                        }).show();
    }

    private void mySetErrorCheck() {
        new AlertDialog.Builder(this,R.style.DialogTheme)
                .setTitle("Error")
                .setMessage("Debes aceptar la condiciones y politicas de privacidad para continuar")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                              /*  Toast.makeText(getApplicationContext(),
                                        "You clicked on YES", Toast.LENGTH_SHORT)
                                        .show();*/
                            }
                        }).show();

    }
    private void showinfoAfilacion() {
        new AlertDialog.Builder(this,R.style.DialogTheme)
                .setTitle("Aviso")
                .setMessage("Donacion ")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                              /*  Toast.makeText(getApplicationContext(),
                                        "You clicked on YES", Toast.LENGTH_SHORT)
                                        .show();*/
                            }
                        }).show();
    }
    private void showTerminos() {
        android.app.FragmentManager fm = getFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogTheme);
        dialogFragment.show(fm, "Terminos y politica de privacidad");
    }

    public void mySetLineError(View viewLine){
        viewLine.setBackgroundColor(getResources().getColor(R.color.editError700));
    }


    public void mySetError (EditText edittext){
        edittext.setHintTextColor(getResources().getColor(R.color.editError700));
        edittext.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_error_rojo,0);
        edittext.getBackground().setColorFilter(getResources().getColor(R.color.editError700), PorterDuff.Mode.SRC_ATOP);
    }
}
