package com.example.collegelife.ui.main;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private String Name;
    private double GPA;
    private int Debt;
    private String Icon;
    private boolean playState;
    private ImageView CurrentBlock;

    // constructor
    Character(String name) {
        Name = name;
        GPA = 4;
        Debt = 0;
        Icon = "";
        CurrentBlock = null;
        playState = true;
    }



    ///////// methods /////////
    public String getName(){
        return this.Name;
    }

    public String getGPA(){
        String gpa = "";
        gpa += this.GPA;
        return gpa;
    }

    public void setGPA(int gpa){
        double temp = this.GPA;
        this.GPA = (temp + gpa)/2;
    }

    public String getDebt(){
        return "" + this.Debt;
    }

    public void addDebt(double amount){
        this.Debt -= amount;
    }

    public void subtractDebt(double amount){
        this.Debt += amount;
    }

    String getIcon(){
        return this.Icon;
    }

    void setIcon(String icon){
        this.Icon = icon;
    }

    ImageView getCurrentBlock(){
        return this.CurrentBlock;
    }

    void setCurrentBlock(ImageView block){
        this.CurrentBlock = block;
    }


}
