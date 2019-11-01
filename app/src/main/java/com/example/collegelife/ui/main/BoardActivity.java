package com.example.collegelife.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collegelife.R;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "Board_Activity";
    private ArrayList<Character> character = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Button spin = findViewById(R.id.SpinButton);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), SpinboardActivity.class);
                //intent1.putExtra("Player_name", player1.getName());
                view.getContext().startActivity(intent1);
            }
        });

        // get info from calling fragment
        Intent intent = getIntent();
        // multiplayer
        String[] players = intent.getStringArrayExtra("players");
        for (String player_name: players) {
            Character player = new Character(player_name);
            character.add(player);
        }
        Log.d(TAG, character.toString());
        //TextView showName = findViewById(R.id.Player_Name);
        //showName.setText(player1.getName());
        //TextView showGPA = findViewById(R.id.GPA);
       // showGPA.setText(player1.getGPA());

        //Button play = findViewById(R.id.player_turn);
        //play.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View view) {

                //Intent intent1 = new Intent(view.getContext(), BoardActivity.class);
               // intent1.putExtra("Player_name", player1.getName());
                //view.getContext().startActivity(intent1);
            //}
        //});
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called");
    }

    @Override
    // Shoulde be called before onPause
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
    }

    @Override
    // Should be called after onStart and onResume
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}

