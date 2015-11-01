package com.pyrospiral.android.templateapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_main, container, false);

        BootstrapButton applist = (BootstrapButton) root.findViewById(R.id.applistbutton);
        applist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apps = new Intent(getActivity(),AppDrawer.class);
                startActivity(apps);
            }
        });

        return root;
    }


}
