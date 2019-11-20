package com.example.collegelife.ui.main;

import android.widget.ImageView;

import java.io.Serializable;

public class Character implements Serializable {

    private String Name;
    private double GPA;
    private int Debt;
    private String Icon;
    private ImageView CurrentBlock;

    // constructor
    Character(String name) {
        Name = name;
        GPA = 4;
        Debt = 0;
        Icon = "";
        CurrentBlock = null;
    }



    ///////// methods /////////
    public String getName(){
        return this.Name;
    }

    String getGPA(){
        String gpa = "";
        gpa += this.GPA;
        return gpa;
    }

    void setGPA(int gpa){
        double temp = this.GPA;
        this.GPA = (temp + gpa)/2;
    }

    String getDebt(){
        return "" + this.Debt;
    }

    void addDebt(double amount){
        this.Debt -= amount;
    }

    void subtractDebt(double amount){
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
