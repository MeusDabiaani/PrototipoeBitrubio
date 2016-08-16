package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.Metas.FragmentMetaAgua;
import com.bitrubio.prototipoebitrubio.Metas.FragmentMetaEjercicio;
import com.bitrubio.prototipoebitrubio.Metas.FragmentMetaSueno;
import com.bitrubio.prototipoebitrubio.Metas.FragmentQuitarVicios;
import com.bitrubio.prototipoebitrubio.Util.AjustaImagen;
import com.bitrubio.prototipoebitrubio.Util.DocumentExifTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Orion on 27/04/2016.
 * muestra el frgamento seleccionado desde las lista de metas
 */
public class FragmentMetaSelecionada extends Fragment {
    private static final int ROTATION_DEGREES = 90;
    @Bind(R.id.image_foto)
    ImageView _image_foto;

    @Bind(R.id.btn_foto)
    ImageView _btn_foto;

/*    @Bind(R.id.edit_nomMeta)
    EditText edit_nomMeta;*/

    String stNombreMeta;

    public byte[] byteArray;
    String ba1;
    ConectaServidor servidor ;
    public  String URL = "subeFoto.php";
    String idUsuario;
    private String TAG = getClass().getSimpleName();
    ProgressDialog pd;
    int RESULT_OK = -1;
    Bitmap imagen;
    FragmentTransaction FT;
    int fragmentoSeleccionado, tipoMeta;
    Typeface tf;
    Toolbar toolbar;
    int selecionPeso, pesoActual, pesoObjetivo;
    SessionManager session;
    public FragmentMetaSelecionada() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = null;
        //recibo el id de la meta seleccionada
        Bundle bundle = this.getArguments();
        Log.w(TAG, "Clase selecionada : " + TAG);
        fragmentoSeleccionado = bundle.getInt("position", 0);
        tipoMeta = bundle.getInt("tipoMeta", 0);
        servidor = new ConectaServidor();
        URL = servidor.getUrl()+URL;
        session = new SessionManager(getContext().getApplicationContext());
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        idUsuario = user.get(SessionManager.KEY_IDUSER);


        Log.e(TAG,"url"+URL);

        if (tipoMeta == 1) {
            view = inflater.inflate(R.layout.fragment_meta_peso, container, false);
        } else if (tipoMeta == 2) {
            view = inflater.inflate(R.layout.fragment_meta_bienestar, container, false);
        } else if (tipoMeta == 3) {
            view = inflater.inflate(R.layout.fragment_meta_enfermedad, container, false);
        }

        _image_foto = (ImageView) view.findViewById(R.id.btn_foto);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        ButterKnife.bind(this, view);





        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");

        if (savedInstanceState == null) {
            final Bundle args = new Bundle();
            if (fragmentoSeleccionado == 1) {
                mTitle.setText(R.string.peso);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_peso));
                Fragment fragment = new FragmentPeso();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 2) {
                mTitle.setText(R.string.alimentacion);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_alimentacion));

            }
            if (fragmentoSeleccionado == 3) {
                mTitle.setText(R.string.ejercicio);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_ejercicio));
                Fragment fragment = new FragmentMetaEjercicio();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 4) {
                mTitle.setText(R.string.tomar_agua);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_agua));
                Fragment fragment = new FragmentMetaAgua();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 5) {
                mTitle.setText(R.string.sueno);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_sueno));
                Fragment fragment = new FragmentMetaSueno();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 6) {
                mTitle.setText(R.string.quitar_vicios);
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_vicios));
                Fragment fragment = new FragmentQuitarVicios();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }

            if (fragmentoSeleccionado == 7) {
                mTitle.setText(R.string.ayudar);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_ayudar));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 8) {
                mTitle.setText(R.string.desarrollarme);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_desarrollarme));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 9) {
                mTitle.setText(R.string.reflexionar);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_reflexionar));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 10) {
                mTitle.setText(R.string.divertirme);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.divertirme));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 11) {
                mTitle.setText(R.string.estar_otros);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_otros));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }


            if (fragmentoSeleccionado == 12) {
                mTitle.setText(R.string.medicamentos);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_medicamentos));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 13) {
                mTitle.setText(R.string.laboratorios);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_laboratorios));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 14) {
                mTitle.setText(R.string.niveles);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_terapias));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 15) {
                mTitle.setText(R.string.auto_chequeo);
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                _image_foto.setImageDrawable(getResources().getDrawable(R.drawable.fondo_chequeo));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }

        }
        guardaFoto();


        return view;
    }


    public void guardaFoto() {

        //_btnfoto Perfil
        final Item[] items = {new Item("Seleccionar de tu galería", R.drawable.ic_tu_galeria),
                new Item("Captura una foto nueva", R.drawable.ic_camara_simple)
        };
        final ListAdapter adapter = new ArrayAdapter<Item>(
                getContext(),
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

        _btn_foto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialogCamara(adapter,items);
            }
        });

        _image_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCamara(adapter,items);
            }
        });
    }

    public  void showDialogCamara (ListAdapter adapter, final Item[] items){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                if (thumbnail != null) {
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                    File destination = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".jpg");
                    FileOutputStream fo;
                    try {
                        destination.createNewFile();
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();

                        byteArray = bytes.toByteArray();
                        ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        new uploadToServer().execute();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    _image_foto.setImageBitmap(thumbnail);
                }

            }
        }
        if (requestCode == 2) { // foto tomada de la galeria
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                try {
                    Bitmap newBitmap ;
                    newBitmap =  new AjustaImagen(getActivity(),_image_foto,uri).ajustarSize50();
                    new AjustaImagen(getActivity(),_image_foto,uri).rotateImagen();
                    _image_foto.setImageBitmap(newBitmap);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    newBitmap.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                    byteArray = bytes.toByteArray();
                    ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    new uploadToServer().execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public class uploadToServer extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity(),R.style.DialogTheme);
            pd.setMessage("Subiendo Imagen!");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("base64", ba1));
            nameValuePairs.add(new BasicNameValuePair("ImageName", "fotoMeta_" + idUsuario + ".jpg"));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL);
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                String st = EntityUtils.toString(response.getEntity());
                Log.e(TAG,"st " +st );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();
        }
    }
}