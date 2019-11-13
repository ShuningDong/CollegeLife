package com.example.collegelife.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegelife.R;

public class CharacterActivity extends AppCompatActivity {
    private static final String TAG = "Character_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        CharacterFragment charMenu = new CharacterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, charMenu).commit();

        Log.d(TAG, "onCreate is called for character activity");

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
        CharacterActivity.super.onBackPressed();
        finish();
        Log.d(TAG, "onStop is called");
    }

/*    @Override
    // Shoulde be called before onPause
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
    }

    @Override
    // Should be called after onStart and onResume
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }*/
}
