package com.bitrubio.prototipoebitrubio.AsynkData;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Orion on 27/07/2016.
 */
public class AsyncTaskFondo extends AsyncTask<Void, Void, String> {
    String TAG = getClass().getName();
    Context context;
    ProgressDialog pDialog ;

    String URL;
    ConectaServidor servidor;
    String base64;
    String idUsuario;

    public AsyncTaskFondo(Context ctx,String url, String ba1, String idUser) {
        context = ctx;
        this.URL= url;
        this.base64 = ba1;
        this.idUsuario = idUser;

    }

    protected void onPreExecute() {
        super.onPreExecute();
        pDialog =  new ProgressDialog(context, R.style.AppTheme_Dark_Dialog); ;
        CharSequence charSequence = "Subiendo imagen";
        pDialog.setMessage(charSequence);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setProgress(0);
        pDialog.setMax(100);
        pDialog.show();
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pDialog.hide();
        pDialog.dismiss();
    }

    @Override
    protected String doInBackground(Void... params) {
        servidor = new ConectaServidor();
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("base64", base64));
        nameValuePairs.add(new BasicNameValuePair("ImageName", idUsuario + ".jpg"));
        URL = servidor.getUrl() + URL;
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String st = EntityUtils.toString(response.getEntity());

            Log.e(TAG, "asytaskREsult" + st);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
