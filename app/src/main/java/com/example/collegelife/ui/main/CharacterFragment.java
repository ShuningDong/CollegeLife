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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegelife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment {
    Character player1;
    Character player2;
    Character player3;
    Character player4;

    Character[] play = {};

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
                    player1 = new Character(edit_text.getText().toString());
                }
                //startActivity(new Intent(getActivity().getApplicationContext(), popup_Passclass.class));
            }
        });
        Button next_button = v.findViewById(R.id.next_player);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name!", Toast.LENGTH_SHORT).show();
                }else {
                    player1 = new Character(edit_text.getText().toString());
                    Log.d(TAG, player1.getName());

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
