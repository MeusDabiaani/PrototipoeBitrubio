package com.bitrubio.prototipoebitrubio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * Created by Orion on 02/06/2016.
 */
public class FragmentDetall_Agradecer extends Fragment implements OnChartValueSelectedListener {
    String TAG = getClass().getName();

    protected HorizontalBarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    private Typeface tf;

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        setData(12, 10);
        mChart.animateY(2500);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meta_fisic_det_3,container,false);
        mChart = (HorizontalBarChart) view.findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        // mChart.setHighlightEnabled(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        mChart.setMaxVisibleValueCount(60);

        mChart.setPinchZoom(false);


        mChart.setDrawGridBackground(false);



        tf= Typeface.createFromAsset(getContext().getAssets(),
                "fonts/avenir-light.ttf");

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(tf);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);

        xl.setGridLineWidth(0.2f);


        YAxis yl = mChart.getAxisLeft();
        yl.setTypeface(tf);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);

        yl.setAxisMinValue(0f); // this replaces setStartAtZero(true)


        YAxis yr = mChart.getAxisRight();
        yr.setTypeface(tf);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);


        yr.setAxisMinValue(0f); // this replaces setStartAtZero(true)



        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        return view;
    }
    private void setData(int count, float range) {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        String[] mPerson = new String[]{"Mario","Roberto","Albreto","Vanessa"};
        for (int i = 0; i < mPerson.length; i++) {

            xVals.add(mPerson[i % 4]);
            yVals1.add(new BarEntry((float) (Math.random() * range), i));
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals1);
            set1.setColor(getResources().getColor(R.color.letraVerde1));
            mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Apoyo");
            set1.setColor(getResources().getColor(R.color.letraVerde1));
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(xVals, dataSets);

            data.setValueTextSize(10f);
            data.setValueTypeface(tf);
            mChart.setData(data);


        }
    }
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}

