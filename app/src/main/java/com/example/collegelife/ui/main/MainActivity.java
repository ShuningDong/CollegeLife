package com.example.collegelife.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.collegelife.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menufragment mainMenu = new menufragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mainMenu).commit();
    }
}
