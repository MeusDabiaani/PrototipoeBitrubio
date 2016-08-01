package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.ButterKnife;

/**
 * Created by Orion on 22/06/2016.
 */
public class FragmentAgenda extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.dialog_datepiker, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

}
