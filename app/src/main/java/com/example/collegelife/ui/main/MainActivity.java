package com.example.collegelife.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegelife.R;

public class MainActivity extends AppCompatActivity {

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

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



}
