package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AsynkData.AsyncTaskFondo;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Orion on 06/05/2016.
 * dialog fragment para cambiar el fondo de la pantalla
 */
public class Cambiafondo extends DialogFragment implements View.OnClickListener{

    TextView tituloA,tituloB,title_dialog;
    String  idUsuario ;
    public byte[] byteArray;
    String ba1;
    Typeface font;
    ConectaServidor servidor;
    LinearLayout lnrGaleria, lnrTomaFoto;
    public Cambiafondo(){}
    String TAG = getClass().getSimpleName();
    String URL = "bitrubio/movil/subeFondoYo.php";
    AsyncTaskFondo subeImagenFondo;

    public interface CambiafondoListener {
        void setBackgroundFondo(int int_drawable);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_sel_fondo, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ViewGroup linearLayout = (ViewGroup) view.findViewById(R.id.linear_imagenes);
        servidor = new ConectaServidor();

        font= Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        lnrGaleria = (LinearLayout) view.findViewById(R.id.lnr_galeria);
        lnrTomaFoto = (LinearLayout) view.findViewById(R.id.lnr_tomaFoto);

        lnrTomaFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(intent, 3);
            }
        });
        lnrGaleria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
                getActivity().startActivityForResult(Intent.createChooser(intent, "Seleciona foto"), 4);
            }
        });

        tituloA = (TextView) view.findViewById(R.id.opcion_2);
        tituloA.setTypeface(font);
        tituloA.setTextSize(16);
        tituloB = (TextView) view.findViewById(R.id.opcion_3);
        tituloB.setTypeface(font);
        tituloB.setTextSize(16);

        title_dialog = (TextView) view.findViewById(R.id.title_dialog);
        title_dialog.setTypeface(font);
        title_dialog.setText("Elije una imagen");
        title_dialog.setTextSize(16);


        // agrega las imagenes de default para cambiar el fondo de la palicacion
        for (int x = 1; x<5 ;x++) {
            LinearLayout childLayout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()); // margin left para el linearLayout
            int marginRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()); // margin left para el linearLayout
            linearParams.setMargins(marginLeft, 0, marginRight, 0);
            childLayout.setLayoutParams(linearParams);
            childLayout.setGravity(Gravity.CENTER);

            String varDrawable = "pictures_"+x;
            int id = getContext().getResources().getIdentifier(varDrawable, "drawable", getContext().getPackageName());

            ImageView picture = new ImageView(getActivity());
            int weight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 270, getResources().getDisplayMetrics());
            picture.setLayoutParams(new LinearLayout.LayoutParams(weight, height));
            picture.setImageDrawable(getResources().getDrawable(id));
            //picture.setImageResource(id);
            picture.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            picture.setPadding(4,4,4,4);
            picture.setId(id);
            picture.setOnClickListener(this);
            childLayout.addView(picture);
            linearLayout.addView(childLayout);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.drawable.pictures_1){
           // Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
            Bitmap imgFondo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.pictures_1);
            guardarImagen(imgFondo);
            CambiafondoListener activity = (CambiafondoListener) getActivity();
            activity.setBackgroundFondo(R.drawable.fondo_bity);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imgFondo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();
            ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
          //  new uploadFondo().execute();
            subeImagenFondo = new AsyncTaskFondo(getActivity(),URL,ba1,idUsuario);
            subeImagenFondo.execute();

            getDialog().dismiss();

        }
        if (v.getId()==R.drawable.pictures_2){
            Bitmap imgFondo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.pictures_2);
            guardarImagen(imgFondo);
            CambiafondoListener activity = (CambiafondoListener) getActivity();
            activity.setBackgroundFondo(R.drawable.pictures_2);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imgFondo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();
            ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            //  new uploadFondo().execute();
            subeImagenFondo = new AsyncTaskFondo(getActivity(),URL,ba1,idUsuario);
            subeImagenFondo.execute();
            getDialog().dismiss();
        }
        if (v.getId()==R.drawable.pictures_3){
            Bitmap imgFondo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.pictures_3);
            guardarImagen(imgFondo);
            CambiafondoListener activity = (CambiafondoListener) getActivity();
            activity.setBackgroundFondo(R.drawable.pictures_3);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imgFondo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();
            ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            //  new uploadFondo().execute();
            subeImagenFondo = new AsyncTaskFondo(getActivity(),URL,ba1,idUsuario);
            subeImagenFondo.execute();
            getDialog().dismiss();
        }
        if (v.getId()==R.drawable.pictures_4){
            Bitmap imgFondo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.pictures_4);
            guardarImagen(imgFondo);
            CambiafondoListener activity = (CambiafondoListener) getActivity();
            activity.setBackgroundFondo(R.drawable.pictures_4);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imgFondo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();
            ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            //  new uploadFondo().execute();
            subeImagenFondo = new AsyncTaskFondo(getActivity(),URL,ba1,idUsuario);
            subeImagenFondo.execute();
            getDialog().dismiss();
        }


    }
    private String guardarImagen(Bitmap imagen) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byteArray = stream.toByteArray();
        File destination = new File(Environment.getExternalStorageDirectory(),"imagenFondo.jpg");
        FileOutputStream outputStream ;
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
}
