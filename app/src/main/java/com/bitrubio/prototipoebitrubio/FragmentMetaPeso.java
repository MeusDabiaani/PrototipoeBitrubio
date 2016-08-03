package com.bitrubio.prototipoebitrubio;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Metas.TiempoMeta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
 */
public class FragmentMetaPeso extends Fragment  {
    @Bind(R.id.image_foto)
    ImageView _image_foto;
    @Bind(R.id.btn_foto)
    ImageView _btn_foto;
    public byte[] byteArray;
    String ba1;
    public static String URL = "http://www.meustech.com:8080/bitrubio/movil/subeFoto.php";
    String idUsuario;
    private String TAG = getClass().getName();
    ProgressDialog pd;
    int RESULT_OK = -1;
    Bitmap imagen;
    FragmentTransaction FT;
    int fragmentoSeleccionado,tipoMeta;
    Typeface tf;
    Toolbar toolbar;
    public FragmentMetaPeso() {}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = null;

        //recibo el id de la meta seleccionada
        Bundle bundle = this.getArguments();
        fragmentoSeleccionado = bundle.getInt("position", 0);
        tipoMeta = bundle.getInt("tipoMeta",0);
        if(tipoMeta == 1 ){
             view = inflater.inflate(R.layout.fragment_meta_peso, container, false);
        }else if(tipoMeta == 2){
            view = inflater.inflate(R.layout.fragment_meta_bienestar, container, false);
        }else if(tipoMeta == 3){
            view = inflater.inflate(R.layout.fragment_meta_enfermedad, container, false);
        }
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);


        ButterKnife.bind(this, view);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        if (savedInstanceState == null) {
            if (fragmentoSeleccionado == 1) {
                mTitle.setText("Peso");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 2) {
                mTitle.setText("Alimentación");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 3) {
                mTitle.setText("Ejercicio");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 4) {
                mTitle.setText("Tomar agua");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 5) {
                mTitle.setText("Sueño");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 6) {
                mTitle.setText("Quitar vicios");
                mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
                Fragment fragment = new FragmentDetalle_A();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }

            if (fragmentoSeleccionado == 7) {
                mTitle.setText("Ayudar");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 8) {
                mTitle.setText("Desarrollarme");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 9) {
                mTitle.setText("Reflexionar");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 10) {
                mTitle.setText("Divertirme");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 11) {
                mTitle.setText("Estar con otros");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoNaranja));
                Fragment fragment = new FragmentAyudar();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_bienestar, fragment);
                FT.commit();
            }


            if (fragmentoSeleccionado == 12) {
                mTitle.setText("Medicamentos");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 13) {
                mTitle.setText("Laboratorios");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 14) {
                mTitle.setText("Niveles");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
                Fragment fragment = new FragmentDetMedicamento();
                FT = getFragmentManager().beginTransaction();
                FT.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FT.add(R.id.fragment_detalle_metas, fragment);
                FT.commit();
            }
            if (fragmentoSeleccionado == 15) {
                mTitle.setText("Auto Chequeo");
                mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
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
        _btn_foto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"Tomar Foto", "Seleccionar de tu biblioteca", "Cancelar"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dark_Dialog);
                builder.setTitle("Agregar Foto");
                builder.setIcon(R.drawable.ic_subir_foto);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Tomar Foto")) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);

                        } else if (items[item].equals("Seleccionar de tu biblioteca")) {
                            Intent intent = null;

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
                            startActivityForResult(
                                    Intent.createChooser(intent, "Seleciona foto"), 2);
                        } else if (items[item].equals("Cancelar")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _image_foto.setImageBitmap(thumbnail);
                guardarImagen(redimensionarImagenMaximo(thumbnail, 90, 90));
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                _image_foto.setImageURI(data.getData());
                //utilizamos el atrbuti tag para almacenar la uri al archivo seleccionado
                _image_foto.setTag(data.getData());
                imagen = ((BitmapDrawable) _image_foto.getDrawable()).getBitmap();
                guardarImagen(redimensionarImagenMaximo(imagen, 100, 100));
            }
        }

    }

    private String guardarImagen(Bitmap imagen) {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open("imagen.png");
            imagen = BitmapFactory.decodeStream(inputStream);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        byteArray = stream.toByteArray();

        ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        new uploadToServer().execute();
        try {
            FileOutputStream outputStream = getActivity().getApplicationContext().openFileOutput("imagen.png", Context.MODE_PRIVATE);
            outputStream.write(byteArray);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public class uploadToServer extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Subiendo Imagen!");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("base64", ba1));
            nameValuePairs.add(new BasicNameValuePair("ImageName", "" + idUsuario + ".jpg"));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL);
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                String st = EntityUtils.toString(response.getEntity());
                Log.e(TAG, "response " + st);
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
}