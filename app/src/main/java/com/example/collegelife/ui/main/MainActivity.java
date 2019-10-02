package com.example.collegelife.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegelife.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menufragment mainMenu = new menufragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.container, mainMenu).commit();


        Button start_button = (Button) findViewById(R.id.startbutton);

        start_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //move to player page after click the start button
                Intent startIntent = new Intent(getApplicationContext(), player1page.class);
                startActivity(startIntent);
            }
        });

        Log.d(TAG, "onCreate is called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");

    }



}
