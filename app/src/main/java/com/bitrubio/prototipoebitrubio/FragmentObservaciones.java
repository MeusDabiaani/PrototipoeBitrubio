package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Orion on 07/06/2016.
 */
public class FragmentObservaciones extends Fragment {
    Typeface tf;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_observaciones,container,false);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) getActivity().getWindow().findViewById(R.id.toolbar);
        toolbar.setTitle("");

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText("Observaciones");
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        return view;
    }
}
