package com.example.collegelife.ui.main;


import android.os.Bundle;
import android.app.Activity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Log;

import com.example.collegelife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class menufragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Menu_fragment";

    public menufragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.menu_fragment, container, false);

        Button start_button = v.findViewById(R.id.start_button);
        start_button.setOnClickListener(this);
        Button settings_button = v.findViewById(R.id.settings_button);
        settings_button.setOnClickListener(this);

        return v;
    }


    public void onClick(View v) {

        Activity activity =  getActivity();
        if (activity != null) {
            switch (v.getId()) {

                case R.id.start_button:
                    //move to player page after click the start button
                    startActivity(new Intent(activity.getApplicationContext(), player1page.class));
                    break;

                case R.id.settings_button:
                    //move to settings menu after clicking gear icon
                    //startActivity(new Intent(activity.getApplicationContext(), player1page.class));
                    Log.d(TAG, "settings menu coming soon");
                    break;
            }
        }
    }


}
