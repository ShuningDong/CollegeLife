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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collegelife.R;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment {
    private static final String TAG = "Character_Fragment";

    public CharacterFragment() {
        // Required empty public constructor
    }

    private String player;
    private int pcount = 0;
    private int tcount = 0;
    // multiplayer
    private String[] players = new String[4];
    private String[] token = new String[4];
    private boolean hasPicked = false;
    private ImageView playerImage;

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
                    Toast.makeText(getActivity(), R.string.NeedName, Toast.LENGTH_SHORT).show();
                }else {
                    player = edit_text.getText().toString();
                    players[pcount] = player;
                    pcount++;
                    if(tcount == pcount) {
                        Activity activity = getActivity();
                        if (activity != null) {
                            Intent intent = new Intent(activity, BoardActivity.class);
                            intent.putExtra("players", players);
                            intent.putExtra("tokens", token);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "Game Activity is null");
                        }
                    } else {
                        Toast.makeText(getActivity(), R.string.NeedLogo, Toast.LENGTH_SHORT).show();
                        pcount--;
                    }

                }
            }
        });
        Button next_button = v.findViewById(R.id.next_player);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), R.string.NeedName, Toast.LENGTH_SHORT).show();
                }else {
                    if (tcount == (pcount + 1)) {


                        player = edit_text.getText().toString();
                        players[pcount] = player;
                        pcount++;
                        edit_text.setText("");
                        hasPicked = false;
                        //call function to hide add player button
                        updateUI(v, pcount);
                        Log.d(TAG, player);
                        Log.d(TAG, "#" + tcount);
                    } else {
                        Toast.makeText(getActivity(), R.string.NeedLogo, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ImageButton spade_button = v.findViewById(R.id.SpadeButton);
        spade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasPicked) {
                    token[tcount] = "spade";
                    v.findViewById(R.id.SpadeButton).setVisibility(View.GONE);
                    hasPicked = true;
                    tcount++;
                }
            }
        });


        ImageButton heart_button = v.findViewById(R.id.HeartButton);
        heart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasPicked) {
                    token[tcount] = "heart";
                    v.findViewById(R.id.HeartButton).setVisibility(View.GONE);
                    hasPicked = true;
                    tcount++;
                }
            }
        });


        ImageButton club_button = v.findViewById(R.id.ClubButton);
        club_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasPicked) {
                    token[tcount] = "club";
                    v.findViewById(R.id.ClubButton).setVisibility(View.GONE);
                    hasPicked = true;
                    tcount++;
                }
            }
        });


        ImageButton diamond_button = v.findViewById(R.id.DiamondButton);
        diamond_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasPicked) {
                    token[tcount] = "diamond";
                    v.findViewById(R.id.DiamondButton).setVisibility(View.GONE);
                    hasPicked = true;
                    tcount++;
                }
            }
        });

        playerImage = v.findViewById(R.id.imageView);

        Log.d(TAG, "onCreate is called");

        return v;
    }

    private void updateUI(View v, int count) {
        if (count >= 3) {

            v.findViewById(R.id.next_player).setVisibility(View.GONE);
            Toast.makeText(getActivity(), R.string.MaxPlayer, Toast.LENGTH_SHORT).show();

        }

        if (count ==1)
            playerImage.setImageResource(R.drawable.player2);
        else if (count ==2)
            playerImage.setImageResource(R.drawable.player3);
        else if (count == 3)
            playerImage.setImageResource(R.drawable.player4);
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
