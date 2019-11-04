package com.example.collegelife.ui.main;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.collegelife.R;



public class gridDatabase extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupcard);
        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper.addData("1", "Register a class","Welcome !!\n" +
                "You need to register for class", "0","-100001");
        mDatabaseHelper.addData("2", "community service","You attend a community service! Trying to give back to the society", "0","0");
        mDatabaseHelper.addData("3", "Fall break trip","Finally Break time !! You went to Florida for fun\n", "0","-200");
        mDatabaseHelper.addData("4", "Rent an apartment","You were kick off from your parent home\n" +
                "You need to rent an apartment", "0","-500");
        mDatabaseHelper.addData("5", "Buy a pet","You feel lonely You decide to buy a dog", "0","-200");
        mDatabaseHelper.addData("6", "dropping a class","You fail your mid-term exam\n" +
                "You decide to drop the class a retake\n", "0","0");
        mDatabaseHelper.addData("7", "join a club","You enter the Mobile App Development Club\n" +
                "You start to build your own mobile app", "0","0");
        mDatabaseHelper.addData("8", "Spring break trip","Break time Again!! \n" +
                "You went to Chicago for fun ", "0","-200");
        mDatabaseHelper.addData("9", "miss class","You overslept\n" +
                "You miss the most important class", "-0.1","0");
        mDatabaseHelper.addData("10", "buy food","You feel hungry \n" +
                "It is grocery shopping time", "0","-50");
        mDatabaseHelper.addData("11", "pay utilities bills","Opp! Your electricity went off!\n" +
                "Did you pay your utilities bill?\n", "0","-100");
        mDatabaseHelper.addData("12", "get grade from a class","Finals finally over ~~\n" +
                "Here is your grade\n", "0","0");
    }
}
