package com.example.collegelife.ui.main;


import android.os.Bundle;
import android.app.Activity;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegelife.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment {
    private static final String TAG = "Character_Fragment";

    public CharacterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_character, container, false);

        EditText edit_text = v.findViewById(R.id.enter_name);
        Button start_button = v.findViewById(R.id.start_game);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name!", Toast.LENGTH_SHORT).show();
                }else {
                    String player = edit_text.getText().toString();
                    Activity activity = getActivity();
                    if (activity != null) {
                        Intent intent = new Intent(activity, GameActivity.class);
                        intent.putExtra("Player_name", player);
                        startActivity(intent);
                    }else {
                        Log.d(TAG, "Game Activity is null");
                    }

                }
            }
        });
        Button next_button = v.findViewById(R.id.next_player);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name!", Toast.LENGTH_SHORT).show();
                }else {
                    String player1 = edit_text.getText().toString();
                    Log.d(TAG, player1);
                    CharacterFragment fragment = new CharacterFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(CharacterFragment.class.getSimpleName())
                            .commit();
                }
            }
        });



        Log.d(TAG, "onCreate is called");

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called");
    }

}
