package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;
/**
 * Created by Orion on 01/07/2016.
 */

public class DetalleReceta extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private MedicamentosAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Medicamento> medicamentoList;
    @Bind(R.id.btn_cotizar)
    Button btnCotizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        ButterKnife.bind(this);
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
        textTitle.setText(getResources().getString(R.string.misRecetas));
        textTitle.setTextSize(16);

        recyclerView();

        btnCotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void recyclerView(){
        int drawable[] = {R.drawable.promo_1,R.drawable.promo_2,R.drawable.promo_3};
        medicamentoList = new ArrayList<>();
        medicamentoList.add(new Medicamento(1,drawable[0],"Tafirol Flex 30 tabletas",
                "Parecetamol 300mg","Asofarma","1 tableta c/12 hrs "));

        medicamentoList.add(new Medicamento(2,drawable[1],"Ranitidina 20 tabletas",
                "Clorihidrato de ranitidina 150mg","Genericos-Genvita","1 tableta c/12 hrs"));

        medicamentoList.add(new Medicamento(3,drawable[2],"Diclofenaco Sodico 30 tabletas",
                "diclofencaco Sodico 50mg","Genericos-Genvita","1 tableta c/12 hrs"));
        medicamentoList.add(new Medicamento(4,drawable[0],"Tafirol Flex 30 tabletas",
                "Parecetamol 300mg","Asofarma","1 tableta c/12 hrs "));

        medicamentoList.add(new Medicamento(5,drawable[1],"Ranitidina 20 tabletas",
                "Clorihidrato de ranitidina 150mg","Genericos-Genvita","1 tableta c/12 hrs"));

        medicamentoList.add(new Medicamento(6,drawable[2],"Diclofenaco Sodico 30 tabletas",
                "diclofencaco Sodico 50mg","Genericos-Genvita","1 tableta c/12 hrs"));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_detalle_recetas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MedicamentosAdapter(medicamentoList,this);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        //TODO row_detalle_receta
        //TODO recycler_detalle_recetas

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
   /*     if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);

        }*/
        return super.onOptionsItemSelected(item);
    }
}
