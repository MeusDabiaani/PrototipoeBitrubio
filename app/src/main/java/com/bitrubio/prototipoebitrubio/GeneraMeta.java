package com.bitrubio.prototipoebitrubio;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.bitrubio.prototipoebitrubio.AsynkData.ServiceHandler;
import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Entidades.GlobalMetaPeso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Orion on 10/08/2016.
 */
public class GeneraMeta extends AsyncTask<String,String,Integer> {
    String TAG = getClass().getSimpleName();
    ConectaServidor conectaServidor;
    ProgressDialog progressDialog;
    GlobalMetaPeso globalMetaPeso;
    Context context;
    ArrayList<NameValuePair> datosPost;
    String success ;
    String URL = "guardaMeta.php" ;

    String servidor ="";
    public GeneraMeta(Context ctx , ArrayList<NameValuePair> datos){
        this.context = ctx;
        this.datosPost = datos;
    }

    private void result(){
        if (success.equals(1)){
            Intent intent = new Intent(context, MetaDetalle.class);
            context.startActivity(intent);
            Log.e(TAG,"result 1 ");
        }else{
            Intent intent = new Intent(context, MetaDetalle.class);
            context.startActivity(intent);
            Log.e(TAG,"result 0" );
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog  =new ProgressDialog(context,R.style.DialogTheme);
        progressDialog.setMessage("Guardando Meta");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected Integer doInBackground(String... params) {
        servidor = conectaServidor.getUrl()+ URL;
        ServiceHandler jsonParser = new ServiceHandler();
        String json = jsonParser.makeServiceCall(URL, ServiceHandler.POST, datosPost);

        try {
            JSONObject Obj = new JSONObject(json);
            JSONArray resultArray = Obj.getJSONArray("regMeta");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject datosObj = resultArray.getJSONObject(i);
                success = datosObj.getString("success");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            result();
        }
    }
}
