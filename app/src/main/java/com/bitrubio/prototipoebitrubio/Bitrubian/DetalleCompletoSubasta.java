package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.TexviewAvenir;
import com.bitrubio.prototipoebitrubio.R;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.wearable.internal.StorageInfoResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 12/07/2016.
 */
public class DetalleCompletoSubasta extends AppCompatActivity {
    Toolbar toolbar;
    SessionManager session;
    String TAG = getClass().getSimpleName();
    Constantes mConstantes;

    @Bind(R.id.imgMedico)
    ImageView imgMedico;

    @Bind(R.id.txtNombre)
    TextView txtnomMedico;

    @Bind(R.id.txtEspecialidad)
    TextView txtEspecialidad;

    @Bind(R.id.txtFecha)
    TextView txtFecha;

    @Bind(R.id.txtmonto)
    TextView txtMonto;


    @Bind(R.id.txtModoPago)
    TextView txtModoPago;

    @Bind(R.id.txtAseguradoras)
    TextView txtAseguradoras;

    @Bind(R.id.txtIncluye)
    TextView txtIncluye;

    @Bind(R.id.txtSeguimiento)
    TextView txtSeguimiento;

    @Bind(R.id.txtTecnologia)
    TextView txtTecnologia;

    LinearLayout lnrReferecias ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_subastas_completo);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();

        int idMedico = bundle.getInt(mConstantes.C_ID);
        int imagenMedico = bundle.getInt("imgMedico");


        Picasso.with(this).load(imagenMedico).error(R.drawable.doctor_1).into(imgMedico);
        String medico = bundle.getString(mConstantes.C_NOMBRE);
        String especialidad = bundle.getString(mConstantes.C_ESPECIALIDAD);
        String fechaPuja = bundle.getString(mConstantes.C_FECHA);
        String montoPuja =  bundle.getString(mConstantes.C_MONTO);

        String modo = bundle.getString(mConstantes.C_PAGO);
        String aseguradoras = bundle.getString(mConstantes.C_ASEGURADORA);
        String incluye = bundle.getString(mConstantes.C_INCLUYE);
        String seguimiento = bundle.getString(mConstantes.C_SEGUIMIENTO);
        String tecnologia = bundle.getString(mConstantes.C_TECNOLOGIA);
        String referencias[] = bundle.getStringArray(mConstantes.C_REFERENCIAS);
        String  comentarios = bundle.getString(mConstantes.C_COMENTARIO);

        lnrReferecias =  (LinearLayout) findViewById(R.id.lnr_referencias);

        for (int c = 0 ; c <referencias.length;c++){
            TexviewAvenir textView = new TexviewAvenir(this);
            textView.setText(referencias[c]);
            textView.setTextColor(getResources().getColor(R.color.textColorPrimary));
            textView.setTextSize(12);
            lnrReferecias.addView(textView);
        }

        txtnomMedico.setText(medico);
        txtEspecialidad.setText(especialidad);
        txtFecha.setText(fechaPuja);
        txtMonto.setText("$ "+montoPuja);
        txtModoPago.setText(modo);
        txtAseguradoras.setText(aseguradoras);
        txtIncluye.setText(incluye);
        txtSeguimiento.setText(seguimiento);
        txtTecnologia.setText(tecnologia);




        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText("Detalle Puja");
        mTitle.setTextSize(16);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
