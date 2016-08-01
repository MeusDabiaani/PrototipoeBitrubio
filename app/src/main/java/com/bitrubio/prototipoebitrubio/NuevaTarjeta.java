package com.bitrubio.prototipoebitrubio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;
/**
 * Created by Orion on 23/06/2016.
 */
public class NuevaTarjeta extends AppCompatActivity implements DialogColor.CambiaColorListener{
    CardView cardView;
    Toolbar toolbar;
    Typeface tf;
    @Bind(R.id.btn_cambiarColor)
    Button btnCambiarColor;

    @Bind(R.id.img_fondo_card)
    ImageView img_fondo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarjeta);
        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView textTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        textTitle.setText("Nueva Tarjeta");
        textTitle.setTypeface(tf);
        textTitle.setTextSize(14);
        cardView = (CardView) findViewById(R.id.card_view_tarjeta);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
        img_fondo.setBackgroundDrawable(getResources().getDrawable(R.drawable.tarjeta_azul));
        btnCambiarColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showColorDialog();
            }
        });
    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
   /*     if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);
        }*/
        return super.onOptionsItemSelected(item);
    }
    private void showColorDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogColor dialog = new DialogColor();
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
        dialog.show(manager, "titulo");
    }

    @Override
    public void setBackgroundFondo(int int_drawable) {
        Toast.makeText(NuevaTarjeta.this, "int "+int_drawable, Toast.LENGTH_SHORT).show();
        img_fondo.setBackgroundDrawable(getResources().getDrawable(int_drawable));
    }
}
