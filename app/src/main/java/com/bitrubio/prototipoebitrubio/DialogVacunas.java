package com.bitrubio.prototipoebitrubio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 13/07/2016.
 */
public class DialogVacunas extends DialogFragment {

    ListView mainListView;
    Vacunas[] vacunas;
    ArrayAdapter<Vacunas> listAdapter;
    public String TAG = getClass().getSimpleName();

    @Bind(R.id.btn_aceptarVacunas)
    Button btnAceptar;

    @Bind(R.id.title)
    TextView txtTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dialog_vacunas, container, false);
        ButterKnife.bind(this, view);
        mainListView = (ListView) view.findViewById(R.id.mainListView);
        txtTitle.setText("Vacunas");

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item,
                                    int position, long id) {


                Vacunas vacunas = listAdapter.getItem(position);
                vacunas.toggleChecked();
                VacunasViewHolder viewHolder = (VacunasViewHolder) item.getTag();
                viewHolder.getCheckBox().setChecked(vacunas.isChecked());

            }
        });
        // Create and populate planets.
        vacunas = (Vacunas[]) getActivity().getLastNonConfigurationInstance();
        if (vacunas == null) {
            vacunas = new Vacunas[]{
                    new Vacunas(getResources().getString(R.string.todo)),
                    new Vacunas(getResources().getString(R.string.hepatits)),
                    new Vacunas(getResources().getString(R.string.poliomelitis)),
                    new Vacunas(getResources().getString(R.string.rotavirus)),
                    new Vacunas(getResources().getString(R.string.triple)),
                    new Vacunas(getResources().getString(R.string.bcg)),
                    new Vacunas(getResources().getString(R.string.neumococo)),
                    new Vacunas(getResources().getString(R.string.pentavalente)),
                    new Vacunas(getResources().getString(R.string.dtp)),
                    new Vacunas(getResources().getString(R.string.td)),
                    new Vacunas(getResources().getString(R.string.influenza)),
                    new Vacunas(getResources().getString(R.string.tdpa)),
                    new Vacunas(getResources().getString(R.string.vph)),

            };
        }
        ArrayList<Vacunas> vacunasList = new ArrayList<Vacunas>();
        vacunasList.addAll(Arrays.asList(vacunas));

        // Set our custom array adapter as the ListView's adapter.
        listAdapter = new VacunasArrayAdapter(getContext(), vacunasList);
        mainListView.setAdapter(listAdapter);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private static class Vacunas {
        private String name = "";
        private boolean checked = false;

        public Vacunas() {
        }

        public Vacunas(String name) {
            this.name = name;
        }

        public Vacunas(String name, boolean checked) {
            this.name = name;
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String toString() {
            return name;
        }

        public void toggleChecked() {
            checked = !checked;
        }
    }

    /**
     * Holds child views for one row.
     */
    private static class VacunasViewHolder {

        private CheckBox checkBox;
        private TextView textView;

        public VacunasViewHolder() {
        }

        public VacunasViewHolder(TextView textView, CheckBox checkBox) {
            this.checkBox = checkBox;
            this.textView = textView;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Custom adapter for displaying an array of Planet objects.
     */
    private static class VacunasArrayAdapter extends ArrayAdapter<Vacunas> {

        private LayoutInflater inflater;

        public VacunasArrayAdapter(Context context, List<Vacunas> planetList) {
            super(context, R.layout.simplerow_vacunas, R.id.rowTextView, planetList);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Vacunas to display
            Vacunas vacunas = (Vacunas) this.getItem(position);
            // The child views in each row.
            final CheckBox checkBox;
            TextView textView;
            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.simplerow_vacunas, null);
                // Find the child views.
                textView = (TextView) convertView.findViewById(R.id.rowTextView);
                checkBox = (CheckBox) convertView.findViewById(R.id.CheckBox01);
                // Optimization: Tag the row with it's child views, so we don't have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new VacunasViewHolder(textView, checkBox));
                // If CheckBox is toggled, update the planet it is tagged with.
                checkBox.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Log.e("tag", "position" + position);
                        CheckBox cb = (CheckBox) v;

                        Log.e("tag", "checkbox" + cb);
                        Vacunas vacuna = (Vacunas) cb.getTag();

                        vacuna.setChecked(cb.isChecked());


                    }
                });
            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call findViewById().
                VacunasViewHolder viewHolder = (VacunasViewHolder) convertView.getTag();
                checkBox = viewHolder.getCheckBox();
                textView = viewHolder.getTextView();
            }
            // Tag the CheckBox with the Planet it is displaying, so that we can
            // access the planet in onClick() when the CheckBox is toggled.
            checkBox.setTag(vacunas);
            // Display planet data
            checkBox.setChecked(vacunas.isChecked());
            textView.setText(vacunas.getName());
            return convertView;
        }

    }

    public Object onRetainNonConfigurationInstance() {
        return vacunas;
    }
}
